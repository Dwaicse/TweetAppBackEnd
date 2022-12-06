package com.tweetapp.models;

import org.springframework.data.mongodb.core.mapping.Field;

public class Likes {


    @Field(name="Liked By")
    private String emailId;

    public Likes(String emailId) {
        this.emailId = emailId;
    }

}
