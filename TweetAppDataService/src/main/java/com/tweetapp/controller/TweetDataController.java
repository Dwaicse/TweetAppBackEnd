package com.tweetapp.controller;


import com.tweetapp.FeignClients.AuthenticationFeignClient;
import com.tweetapp.models.*;
import com.tweetapp.repositories.TweetDataRepository;
import com.tweetapp.repositories.UserDataRepository;
import com.tweetapp.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin(origins = "*")
public class TweetDataController {


    @Autowired
    ValidationService validationService;

    @Autowired
    TweetDataRepository tweetDataRepository;

    @Autowired
    UserDataRepository userDataRepository;

    @GetMapping("/all")
    public List<Tweets> getAllTweets(@RequestHeader("Authorization") String token) throws Exception {

        ValidationResponse validationResponse;
        validationResponse = validationService.getValidated(token);

        if(!validationResponse.isCredStatus()){
            throw new Exception("Token not validated!");

        }



        return tweetDataRepository.findAll();
    }

    @GetMapping("/getUserAll")
    public List<Tweets> getAllTweetsofuser(@RequestParam("email") String username, @RequestHeader("Authorization") String token) throws Exception {

        ValidationResponse validationResponse;
        validationResponse = validationService.getValidated(token);
        if(!validationResponse.isCredStatus()){
            throw new Exception("Token not validated!");

        }


        return  tweetDataRepository.findAllByEmail(username);


    }

    @PutMapping("/update")
    public Tweets updateTweet(@RequestHeader("Authorization")String token,@RequestParam("id")String tweetId,@RequestBody Tweets tweet) throws Exception {
        ValidationResponse validationResponse;
        validationResponse = validationService.getValidated(token);
        if(!validationResponse.isCredStatus()){
            throw new Exception("Token not validated!");

        }


        Tweets tweets =  tweetDataRepository.findTweetsById(tweetId);
        if(validationResponse.getEmailId().equals(tweets.getEmail())) {
            tweets.setTweetbody(tweet.getTweetbody());

            try {
                tweetDataRepository.save(tweets);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tweets;
        }


        return null;


    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTweet(@RequestHeader("Authorization")String token, @RequestParam("id")String tweetId) throws Exception {
        ValidationResponse validationResponse;
        validationResponse = validationService.getValidated(token);
        if(!validationResponse.isCredStatus()){
            throw new Exception("Token not validated!");

        }


        Tweets tweets =  tweetDataRepository.findTweetsById(tweetId);
        if(validationResponse.getEmailId().equals(tweets.getEmail())) {
            try {
                 tweetDataRepository.deleteById(tweetId);
                 return ResponseEntity.ok(HttpStatus.ACCEPTED);
            }catch (Exception e)
            {
                throw new Exception("Data cannot be deleted! Something went wrong!");
            }
        }

        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/like")
    public String likeTweet(@RequestHeader("Authorization")String token,@RequestParam("id")String tweetId) throws Exception {
        ValidationResponse validationResponse;
        validationResponse = validationService.getValidated(token);
        if(!validationResponse.isCredStatus()){
            throw new Exception("Token not validated!");

        }
        Tweets tweets =  tweetDataRepository.findTweetsById(tweetId);
        List<Likes> tweetsList = tweets.getLikes();
        if(tweetsList==null)
        {
            tweetsList = new ArrayList<>();
        }
        tweetsList.add(new Likes(validationResponse.getEmailId()));
        tweets.setLikes(tweetsList);

        tweetDataRepository.save(tweets);

        Integer total_likes = tweetsList.size();
        return total_likes.toString();


    }


    @PostMapping("/reply")
    public String likeTweet(@RequestHeader("Authorization")String token, @RequestParam("id")String tweetId, @RequestBody Replies replies) throws Exception {
        ValidationResponse validationResponse;
        validationResponse = validationService.getValidated(token);
        if(!validationResponse.isCredStatus()){
            throw new Exception("Token not validated!");

        }
        Tweets tweets =  tweetDataRepository.findTweetsById(tweetId);
        List<Replies> tweetsList = tweets.getReplies();
        if(tweetsList==null)
        {
            tweetsList = new ArrayList<>();
        }
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        tweetsList.add(new Replies(validationResponse.getEmailId(),date,replies.getReply()));
        tweets.setReplies(tweetsList);


        tweetDataRepository.save(tweets);

        Integer total_replies = tweetsList.size();
        return total_replies.toString();


    }

    @GetMapping("/users/all")
    public List<User> getAllUser(@RequestHeader("Authorization")String token) throws Exception {

        ValidationResponse validationResponse;
        validationResponse = validationService.getValidated(token);
        if(!validationResponse.isCredStatus()){
            throw new Exception("Token not validated!");

        }
        return userDataRepository.findAll();

    }









}
