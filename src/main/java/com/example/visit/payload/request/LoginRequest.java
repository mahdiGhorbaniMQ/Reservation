package com.example.visit.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


public class LoginRequest {
    @NotBlank @Getter @Setter
    private String email;

    @NotBlank @Getter @Setter
    private String password;
}