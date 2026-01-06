package com.taskhive.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskhive.backend.model.OAuthUserInfo;
import com.taskhive.backend.model.Role;
import com.taskhive.backend.model.User;
import com.taskhive.backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OAuthService oauthService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       OAuthService oauthService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.oauthService = oauthService;
    }
    
    public User processOAuthLogin(String provider, String accessToken) {
        OAuthUserInfo userInfo = oauthService.getUserInfo(provider, accessToken);

        return userRepository.findByEmail(userInfo.getEmail())
                .orElseGet(() -> createOAuthUser(userInfo, provider));
    }

    private User createOAuthUser(OAuthUserInfo userInfo, String provider) {
        User user = User.builder()
                .email(userInfo.getEmail())
                .role(Role.USER)
                .provider(provider.toUpperCase())
                .build();
        return userRepository.save(user);
    }

    public User register(String email, String password) {

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already in use");
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .provider("LOCAL")
                .build();

        return userRepository.save(user);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

}
