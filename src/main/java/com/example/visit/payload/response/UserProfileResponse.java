package com.example.visit.payload.response;

import lombok.Getter;
import lombok.Setter;

public class UserProfileResponse {
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String email;

    public UserProfileResponse(String name,String email){
        this.name = name;
        this.email = email;
    }
}
