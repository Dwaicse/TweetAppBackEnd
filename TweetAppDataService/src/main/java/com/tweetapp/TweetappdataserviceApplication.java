package com.tweetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableMongoRepositories("com.tweetapp.repositories")
public class TweetappdataserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetappdataserviceApplication.class, args);
	}

}
