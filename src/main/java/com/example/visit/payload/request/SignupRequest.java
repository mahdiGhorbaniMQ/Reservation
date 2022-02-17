package com.example.visit.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {
    @Getter @Setter
    @Size(min = 3, max = 40)
    private String name;

    @Size(max = 50)
    @Email
    @Getter @Setter
    private String email;

    @Getter @Setter
    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    @Getter @Setter
    private String password;
}