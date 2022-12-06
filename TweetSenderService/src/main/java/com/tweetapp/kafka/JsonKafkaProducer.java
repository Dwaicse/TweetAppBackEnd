package com.tweetapp.kafka;


import com.tweetapp.models.Tweet;
import com.tweetapp.models.TweetData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JsonKafkaProducer {


    private KafkaTemplate<String, Tweet> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, Tweet> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(TweetData data){
        log.info(String.format("Message sent -> %s",data.toString()));
        Message<TweetData> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC,"tweetapp").build();

        kafkaTemplate.send(message);
    }
}
