package com.tweetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.tweetapp.repositories")
@EnableFeignClients
public class TweetConsumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetConsumerServiceApplication.class, args);
	}

}
