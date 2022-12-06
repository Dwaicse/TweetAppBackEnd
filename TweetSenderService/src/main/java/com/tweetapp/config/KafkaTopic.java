package com.tweetapp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {



    @Bean
    public NewTopic newJsonTopic(){
        return TopicBuilder.name("tweetapp").partitions(3).build();
    }


}
