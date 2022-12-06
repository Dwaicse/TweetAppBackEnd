package com.tweetapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class ValidationResponse {



    private String id;

    private String name;

    private String email;

    private String password;

    public ValidationResponse(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }




}
