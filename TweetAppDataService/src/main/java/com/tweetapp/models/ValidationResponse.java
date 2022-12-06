package com.tweetapp.models;

import lombok.Data;

@Data
public class ValidationResponse {

    private String emailId;
    private boolean credStatus;


    public ValidationResponse(String emailId, boolean credentialsNonExpired) {
        this.emailId=emailId;
        this.credStatus=credentialsNonExpired;
    }


}