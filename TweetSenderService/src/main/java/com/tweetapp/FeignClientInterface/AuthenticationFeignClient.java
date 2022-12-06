package com.tweetapp.FeignClientInterface;


import com.tweetapp.models.ValidationResponse;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.http.HttpResponse;

@FeignClient(value="LoginModule", url = "http://localhost:8082/api/v1.0/tweets")
public interface AuthenticationFeignClient {


    @GetMapping("/currentUserEmailandStatus")
    ValidationResponse validateToken(@RequestHeader("Authorization") String token);

}

