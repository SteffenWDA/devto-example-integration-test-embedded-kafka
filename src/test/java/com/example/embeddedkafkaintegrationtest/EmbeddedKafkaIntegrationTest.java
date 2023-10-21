package com.example.embeddedkafkaintegrationtest;

import com.example.embeddedkafkaintegrationtest.entities.AdditionalUserInformation;
import com.example.embeddedkafkaintegrationtest.model.EnrichedUserData;
import com.example.embeddedkafkaintegrationtest.model.UserData;
import com.example.embeddedkafkaintegrationtest.repositories.AdditionalUserInformationRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@SpringBootTest
@EmbeddedKafka(ports = 9092)
class EmbeddedKafkaIntegrationTest {


	@Autowired
	KafkaTemplate<String, UserData> kafkaTemplate;

	@Autowired
	KafkaAdmin kafkaAdmin;

	@Autowired
	ConsumerFactory<String, UserData> h;

	@Autowired
	AdditionalUserInformationRepository additionalUserInformationRepository;


	@Test
	void executeIntegrationTest() throws InterruptedException, ExecutionException {


		//arrange
		AdditionalUserInformation additionalUserInformation = new AdditionalUserInformation();
		additionalUserInformation.setAdditionalInformation("asd");
		additionalUserInformation.setCustomerNumber("123");
		additionalUserInformationRepository.save(additionalUserInformation);
		CompletableFuture test =kafkaTemplate.send("not-enriched-user-data",new UserData("test","test"));
		var x= test.get();



		//act


		var t= h.createConsumer("test","asd");
		t.subscribe(List.of("enriched-user-data"));

		ConsumerRecord<String, UserData> received = KafkaTestUtils.getSingleRecord(t, "enriched-user-data");

	}

}
