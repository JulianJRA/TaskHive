package com.taskhive.backend.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.taskhive.backend.model.LoginRequest;
import com.taskhive.backend.model.LoginResponse;
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
    public User register(@RequestBody RegisterRequest request){
        return userService.register(request.getName(), request.getEmail(), request.getPassword());
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        String token = jwtUtil.generateToken(request.getEmail());
        return new LoginResponse(token);
    }
}
