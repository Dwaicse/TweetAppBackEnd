package com.tweetapp.services;

import com.tweetapp.models.User;
import com.tweetapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.List;


@Service
public class RegistrationService  {


    @Autowired
    UserRepository userRepository;


    public User createUser(User user) throws Exception
    {
        boolean flag =false;
        List<User>email= userRepository.findByEmail(user.getEmail());
        if(email.size()>0)
        {
           throw new Exception("EmailId Already Exist");
        }
        return userRepository.save(user);
    }

}
