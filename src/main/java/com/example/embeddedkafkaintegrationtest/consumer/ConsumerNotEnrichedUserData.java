package com.example.embeddedkafkaintegrationtest.consumer;


import com.example.embeddedkafkaintegrationtest.entities.AdditionalUserInformation;
import com.example.embeddedkafkaintegrationtest.model.EnrichedUserData;
import com.example.embeddedkafkaintegrationtest.model.UserData;
import com.example.embeddedkafkaintegrationtest.repositories.AdditionalUserInformationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsumerNotEnrichedUserData {

    final String TOPIC_NAME = "not-enriched-user-data";
    private final String ENRICHED_USER_DATA_TOPIC = "enriched-user-data";
    private final KafkaTemplate<String, EnrichedUserData> kafkaTemplate;
    private final AdditionalUserInformationRepository additionalUserInformationRepository;

    public ConsumerNotEnrichedUserData(KafkaTemplate<String, EnrichedUserData> kafkaTemplate, AdditionalUserInformationRepository additionalUserInformationRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.additionalUserInformationRepository = additionalUserInformationRepository;

    }

    @KafkaListener(topics = TOPIC_NAME, id = "myId")
    public void handle(UserData message) {

        Optional<AdditionalUserInformation> additionalUserInformation = additionalUserInformationRepository.findById("123");
        additionalUserInformation.ifPresent(x -> {
            kafkaTemplate.send(ENRICHED_USER_DATA_TOPIC, new EnrichedUserData("a", "b", x.getAdditionalInformation()));
        });
    }

}
