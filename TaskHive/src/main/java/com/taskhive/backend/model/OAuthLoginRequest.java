package com.taskhive.backend.model;

import lombok.Data;

@Data
public class OAuthLoginRequest {
    private String provider;     // GOOGLE o GITHUB
    private String accessToken;  // token obtenido desde frontend
}
