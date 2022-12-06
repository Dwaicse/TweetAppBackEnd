package com.tweetapp.controller;

import com.tweetapp.FeignClientInterface.AuthenticationFeignClient;
import com.tweetapp.kafka.JsonKafkaProducer;
import com.tweetapp.models.Tweet;
import com.tweetapp.models.TweetData;
import com.tweetapp.models.ValidationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin(origins = "*")
@Slf4j
public class TweetSender {

    @Autowired
    private JsonKafkaProducer jsonKafkaProducer;

    @Autowired
    private AuthenticationFeignClient authenticationFeignClient;

    @PostMapping("/add")
    public ResponseEntity<?> sendKafkaTopic(@RequestBody Tweet tweet){

        ValidationResponse validationResponse;
        String token = "Bearer "+tweet.getToken();
        try {
             validationResponse = authenticationFeignClient.validateToken(token);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
        if(validationResponse.isCredStatus())
        {
            TweetData tweetData = new TweetData();

            tweetData.setEmailId(validationResponse.getEmailId());
            log.info(String.format(validationResponse.toString()));
            tweetData.setTweetbody(tweet.getTweetbody());
            jsonKafkaProducer.sendMessage(tweetData);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        if(!validationResponse.isCredStatus())
        {
            return ResponseEntity.ok("Login Session Expired! Kindly login again to continue!");
        }


    return ResponseEntity.ok("Invalid User!");

    }



}
