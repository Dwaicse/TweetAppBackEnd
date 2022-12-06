package com.tweetapp.repositories;

import com.tweetapp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDataRepository extends MongoRepository<User,Integer> {
}
