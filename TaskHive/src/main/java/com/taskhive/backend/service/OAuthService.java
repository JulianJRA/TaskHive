package com.taskhive.backend.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.taskhive.backend.model.OAuthUserInfo;

@Service
public class OAuthService {

    private final RestTemplate restTemplate = new RestTemplate();

    public OAuthUserInfo getUserInfo(String provider, String accessToken) {
        switch (provider.toUpperCase()) {
            case "GOOGLE":
                return getGoogleUserInfo(accessToken);
            case "GITHUB":
                return getGithubUserInfo(accessToken);
            default:
                throw new IllegalArgumentException("Proveedor OAuth desconocido: " + provider);
        }
    }

    private OAuthUserInfo getGoogleUserInfo(String accessToken) {
        String url = UriComponentsBuilder.fromUriString("https://www.googleapis.com/oauth2/v3/userinfo")
                .queryParam("access_token", accessToken)
                .toUriString();

        var response = restTemplate.getForObject(url, Map.class);
        return new OAuthUserInfo(
                (String) response.get("email"),
                (String) response.get("name"),
                (String) response.get("picture")
        );
    }

    private OAuthUserInfo getGithubUserInfo(String accessToken) {
        // Github requiere Authorization header
        var headers = new org.springframework.http.HttpHeaders();
        headers.add("Authorization", "token " + accessToken);
        var entity = new org.springframework.http.HttpEntity<>(headers);

        var response = restTemplate.exchange(
                "https://api.github.com/user",
                org.springframework.http.HttpMethod.GET,
                entity,
                Map.class
        ).getBody();

        String email = getGithubEmail(accessToken); // función separada
        return new OAuthUserInfo(
                email,
                (String) response.get("name"),
                (String) response.get("avatar_url")
        );
    }

    private String getGithubEmail(String accessToken) {
        var headers = new org.springframework.http.HttpHeaders();
        headers.add("Authorization", "token " + accessToken);
        var entity = new org.springframework.http.HttpEntity<>(headers);

        var emails = restTemplate.exchange(
                "https://api.github.com/user/emails",
                org.springframework.http.HttpMethod.GET,
                entity,
                List.class
        ).getBody();

        // obtener email principal
        for (Object e : emails) {
            Map<?, ?> map = (Map<?, ?>) e;

            if (Boolean.TRUE.equals(map.get("primary"))) {
                return (String) map.get("email");
            }
        }


        return null;
    }
}
