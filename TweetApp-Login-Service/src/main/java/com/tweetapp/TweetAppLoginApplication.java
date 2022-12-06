package com.tweetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableMongoRepositories("com.tweetapp.repositories")
public class TweetAppLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetAppLoginApplication.class, args);
	}

}
