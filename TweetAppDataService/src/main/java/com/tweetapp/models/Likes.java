package com.tweetapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
public class Likes {


    @Field(name="LikedBy")
    private String emailId;

    public Likes(String emailId) {
        this.emailId = emailId;
    }


}