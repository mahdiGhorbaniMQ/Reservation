package com.example.visit.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JwtResponse {
    @Getter @Setter private String token;
    @Getter private String type = "Bearer";
    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private String email;
    @Getter @Setter private List<String> roles;

    public JwtResponse(String accessToken, String name, Long id, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }
}