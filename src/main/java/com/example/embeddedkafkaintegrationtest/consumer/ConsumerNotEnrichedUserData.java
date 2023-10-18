package com.example.embeddedkafkaintegrationtest.consumer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerNotEnrichedUserData {
    final String TOPIC_NAME= "not-enriched-user-data";
    @KafkaListener(topics = TOPIC_NAME,id = "myId")
    public void handle(String message){
        log.info("I was called");
    }
}
