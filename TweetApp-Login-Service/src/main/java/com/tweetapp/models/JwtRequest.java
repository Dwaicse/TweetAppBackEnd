package com.tweetapp.models;

import lombok.Data;

@Data
public class JwtRequest {

    private String email;
    private String password;

    public JwtRequest(String email, String password) {
        this.email=email;
        this.password=password;
    }
}
