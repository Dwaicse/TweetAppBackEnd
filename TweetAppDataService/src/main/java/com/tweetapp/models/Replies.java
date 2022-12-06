package com.tweetapp.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@NoArgsConstructor
public class Replies {



    private String emailid;

    private Date replydate;

    @Field(name="Reply")
    private String reply;

    public Replies(String emailid, String reply) {
        this.emailid = emailid;
        this.reply = reply;
    }

    public Replies(String emailid, Date replydate, String reply) {
        this.emailid = emailid;
        this.replydate = replydate;
        this.reply = reply;
    }



}