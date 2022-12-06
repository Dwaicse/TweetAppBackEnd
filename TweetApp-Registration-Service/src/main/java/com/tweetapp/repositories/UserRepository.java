package com.tweetapp.repositories;

import com.tweetapp.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {

    List<User> findByEmail(String email);
}
