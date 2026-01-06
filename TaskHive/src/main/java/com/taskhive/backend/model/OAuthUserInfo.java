package com.taskhive.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OAuthUserInfo {
    private String email;
    private String name;      // opcional, si quieres guardar nombre
    private String avatarUrl; // opcional
}
