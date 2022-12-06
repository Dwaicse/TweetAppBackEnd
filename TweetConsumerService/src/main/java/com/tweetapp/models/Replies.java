package com.tweetapp.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Replies {

    private String emailid;



    @Field(name="Reply")
    private String reply;

    public Replies(String emailid, String reply) {
        this.emailid = emailid;
        this.reply = reply;
    }



}
