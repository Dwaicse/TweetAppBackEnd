package com.tweetapp.services;

import com.tweetapp.FeignClientInterface.AuthenticationFeignClient;
import com.tweetapp.models.TweetData;
import com.tweetapp.models.Tweets;
import com.tweetapp.models.ValidationResponse;
import com.tweetapp.repositories.TweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class TweetService {

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    private AuthenticationFeignClient authenticationFeignClient;

    @KafkaListener(topics = "tweetapp", groupId = "tweetAppGroup")
    public void consume(TweetData tweetConsumedData){
        log.info(String.format("Tweet Recieved -> %s", tweetConsumedData.toString()));


        ValidationResponse validationResponse = authenticationFeignClient.validateEmail(tweetConsumedData.getEmailId());
        // log.info("Validation Email: "+validationResponse.getEmail() + " Tweet Data Email: " + tweetConsumedData.getEmailId());
        if(validationResponse.getEmail().equals(tweetConsumedData.getEmailId().toString()))
        {
            try{
                Tweets tweets = new Tweets();
                tweets.setEmail(tweetConsumedData.getEmailId());
                tweets.setTweetbody(tweetConsumedData.getTweetbody());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                long millis=System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                tweets.setTweetdate(date);
                tweetRepository.save(tweets);

            }catch (Exception e)
            {
                log.info(String.format("Error -> "+e.toString()));
            }
        } else if (!validationResponse.getEmail().equals(tweetConsumedData.getEmailId().toString())) {
            log.info(String.format("Error -> %s","Invalid EmailId"));
        }



        log.info(String.format("Success -> %s", "Tweet Consumed and added to the database successfully!" ));




    }


}
