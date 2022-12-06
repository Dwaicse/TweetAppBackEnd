package com.tweetapp.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DateTimeException;
import java.util.Date;
import java.util.List;

@Document("tweets")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Tweets {

    @Id
    private String id;
    private String email;



    private String tweetbody;
    private List<Replies> replies;
    private List<Likes> likes;

    private Date tweetdate;

    public Tweets(String email, String tweetbody) {
        this.email = email;
        this.tweetbody = tweetbody;
    }

    public Tweets(String email, String tweetbody, List<Replies> replies, List<Likes> likes) {
        this.email = email;
        this.tweetbody = tweetbody;
        this.replies = replies;
        this.likes = likes;
    }

    public Tweets(String email, String tweetbody, List<Replies> replies, List<Likes> likes, Date tweetdate) {
        this.email = email;
        this.tweetbody = tweetbody;
        this.replies = replies;
        this.likes = likes;
        this.tweetdate = tweetdate;
    }



}
