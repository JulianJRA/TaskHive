package com.taskhive.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.taskhive.backend.model.LoginRequest;
import com.taskhive.backend.model.LoginResponse;
import com.taskhive.backend.model.OAuthLoginRequest;
import com.taskhive.backend.model.RegisterRequest;
import com.taskhive.backend.model.User;
import com.taskhive.backend.security.JwtUtil;
import com.taskhive.backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request.getEmail(), request.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            // Autenticación de Spring Security
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            // Cargar usuario para generar token con info completa (email + rol)
            User user = userService.findByEmail(request.getEmail());
            String token = jwtUtil.generateToken(user);

            // Devolver token usando LoginResponse
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(401)
                    .body(new LoginResponse("Usuario o contraseña incorrectos"));
        }
    }
    
    @PostMapping("/oauth-login")
    public ResponseEntity<LoginResponse> oauthLogin(@RequestBody OAuthLoginRequest request) {
        try {
            User user = userService.processOAuthLogin(request.getProvider(), request.getAccessToken());
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new LoginResponse("Error: " + e.getMessage()));
        }
    }

}
