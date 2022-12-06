package com.tweetapp.repositories;

import com.tweetapp.models.Tweets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TweetDataRepository extends MongoRepository<Tweets,String> {

    public List<Tweets> findAll();
    public List<Tweets> findAllByEmail(String email);

    public Tweets findTweetsById(String Id);
}
