package com.tweetapp.FeignClientInterface;


import com.tweetapp.models.ValidationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value="LoginModule", url = "http://localhost:8082/api/v1.0/tweets")
public interface AuthenticationFeignClient {


    @GetMapping("/getCurrentUser/{emailId}")
    ValidationResponse validateEmail(@PathVariable("emailId") String emailId);

}

