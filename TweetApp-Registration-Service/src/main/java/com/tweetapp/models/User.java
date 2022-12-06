package com.tweetapp.models;


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
public class User {

    @Id
    private String Id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String contact;

}
