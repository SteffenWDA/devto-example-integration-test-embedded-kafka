package com.example.embeddedkafkaintegrationtest;

import com.example.embeddedkafkaintegrationtest.entities.AdditionalUserInformation;
import com.example.embeddedkafkaintegrationtest.model.EnrichedUserData;
import com.example.embeddedkafkaintegrationtest.model.UserData;
import com.example.embeddedkafkaintegrationtest.repositories.AdditionalUserInformationRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@EmbeddedKafka(ports = 9092)
class EmbeddedKafkaIntegrationTest {


    @Autowired
    KafkaTemplate<String, UserData> kafkaTemplate;

    @Autowired
    ConsumerFactory<String, EnrichedUserData> h;

    @Autowired
    AdditionalUserInformationRepository additionalUserInformationRepository;


    @Test
    void executeIntegrationTest() throws InterruptedException, ExecutionException {


        //arrange
        final String customerNumber = "customerNumber";
        final String userName = "userName";
        final String interestingAdditionalInformation= "interesting additional information";


        AdditionalUserInformation additionalUserInformation = new AdditionalUserInformation();
        additionalUserInformation.setAdditionalInformation(interestingAdditionalInformation);
        additionalUserInformation.setCustomerNumber(customerNumber);
        additionalUserInformationRepository.save(additionalUserInformation);

        CompletableFuture<SendResult<String, UserData>> test = kafkaTemplate.send("not-enriched-user-data", new UserData(userName, customerNumber));
        var x = test.get();
        var t = h.createConsumer("test", "test");
        t.subscribe(List.of("enriched-user-data"));

        //act


        //assert
        ConsumerRecord<String, EnrichedUserData> receivedRecord = KafkaTestUtils.getSingleRecord(t, "enriched-user-data");
        Assertions.assertAll("",
                () -> assertEquals(userName,receivedRecord.value().getUserName()),
                () -> assertEquals(customerNumber,receivedRecord.value().getCustomerNumber()),
                () -> assertEquals(interestingAdditionalInformation,receivedRecord.value().getEnrichedInfo())
        );

    }

}
