package com.tweetapp.repositories;

import com.tweetapp.models.Tweets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends MongoRepository<Tweets,String> {
}
