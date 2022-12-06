package com.tweetapp.models;

import lombok.Data;

@Data
public class JwtResponse {

   private String token;
   private String email;

   public JwtResponse(String email, String token) {
      this.email=email;
      this.token=token;
   }
}
