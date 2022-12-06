package com.tweetapp.services;

import com.tweetapp.FeignClients.AuthenticationFeignClient;
import com.tweetapp.models.ValidationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidationService {


    @Autowired
    AuthenticationFeignClient authenticationFeignClient;

    public ValidationResponse getValidated(String token) throws Exception {
        ValidationResponse validationResponse;
        String token_updated = "Bearer "+token;
        try {
            log.info(token_updated);
            validationResponse = authenticationFeignClient.validateToken(token_updated);
        }catch (Exception e){
            throw new Exception("Token Cannot be validated!");
        }

        return validationResponse;
    }




}
