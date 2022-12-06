package com.tweetapp.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.tweetapp.config.JwtUtil;
import com.tweetapp.models.JwtRequest;
import com.tweetapp.models.JwtResponse;
import com.tweetapp.models.ValidationResponse;
import com.tweetapp.repositories.UserRepository;
import com.tweetapp.services.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin(origins = "*")
@Slf4j
public class LoginController {

    //Get Mapping

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<?> generateToken(@RequestParam String email, @RequestParam String password) throws Exception {
        JwtRequest jwtRequest =new JwtRequest(email,password);
        System.out.println("Login Request -> "+jwtRequest.getEmail());
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(),jwtRequest.getPassword()));

        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }


        UserDetails user = this.userService.loadUserByUsername(jwtRequest.getEmail());

        String token = this.jwtUtil.generateToken(user);

        JwtResponse jwtResponse = new JwtResponse(jwtRequest.getEmail(),token);
        System.out.println("JWT :" + token);

        return ResponseEntity.ok(jwtResponse);

    }


    @GetMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestParam String newPassword, @RequestParam String email)
    {

        com.tweetapp.models.User user = userRepository.findTopByEmail(email);

        user.setPassword(newPassword);
        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);


    }




    @GetMapping("/currentUser")
    public User getCurrentUser(Principal principal)
    {
        return (User) this.userService.loadUserByUsername(principal.getName());

    }

    @GetMapping("/currentUserEmailandStatus")
    public ValidationResponse getCurrentUserValidation(Principal principal)
    {
        UserDetails user = this.userService.loadUserByUsername(principal.getName());

        ValidationResponse validationResponse = new ValidationResponse(user.getUsername(),user.isCredentialsNonExpired());
        return validationResponse;

    }

    @GetMapping("/getCurrentUser/{emailId}")
    public com.tweetapp.models.User getCurrentUser(@PathVariable("emailId")String email)
    {
        com.tweetapp.models.User user = this.userRepository.findTopByEmail(email);
        user.setPassword("Hidden");
        return  user;
    }




}
