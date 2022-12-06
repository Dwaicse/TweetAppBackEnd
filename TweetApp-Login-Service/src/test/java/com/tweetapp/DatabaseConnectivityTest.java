package com.tweetapp;

import com.tweetapp.models.User;
import com.tweetapp.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DatabaseConnectivityTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userRepository_whenSaveAndRetreiveEntity_thenOK(){

        String name="TestName";
        String email = "TestEmail@abc.com";
        String password = "TestPassword";
        String contact ="";

        User user = userRepository.save(new User(name, email,password,contact));

        User userEntityretrieve = userRepository.findTopByEmail("TestEmail@abc.com");

        Assert.notNull(userEntityretrieve,"Records Retriving Test Passed!!");
        org.junit.Assert.assertEquals(user.getEmail(),userEntityretrieve.getEmail());

    }

}
