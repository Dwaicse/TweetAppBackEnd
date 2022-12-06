package com.tweetapp.controller;

import com.tweetapp.models.User;
import com.tweetapp.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin(origins = "*")
public class RegistrationController {


    @Autowired
    RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody User user) throws Exception
    {
        return new ResponseEntity<>(registrationService.createUser(user),HttpStatus.CREATED);
    }


}
