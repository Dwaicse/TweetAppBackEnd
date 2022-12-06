package com.tweetapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TweetData {




    private String emailId;
    private String tweetbody;

    public TweetData(String emailId, String tweetbody) {
        this.emailId = emailId;
        this.tweetbody = tweetbody;
    }

}
