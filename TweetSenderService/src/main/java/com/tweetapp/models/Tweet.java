package com.tweetapp.models;

import lombok.Data;

@Data
public class Tweet {

    private String token;
    private String tweetbody;

    public Tweet(String token, String tweetbody) {
        this.token = token;
        this.tweetbody = tweetbody;
    }

}
