package com.example.embeddedkafkaintegrationtest;

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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@SpringBootTest
@EmbeddedKafka(ports = 9092)
class EmbeddedKafkaIntegrationTest {


	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	KafkaAdmin kafkaAdmin;

	@Autowired
	ConsumerFactory<String, String> h;



	@Test
	void executeIntegrationTest() throws InterruptedException, ExecutionException {


		//arrange
		CompletableFuture test =kafkaTemplate.send("not-enriched-user-data","asd");

		//act
		var x= test.get();

		System.out.println("not-enriched-user-data");
		var t= h.createConsumer("test","asd");

		ConsumerRecords<String,String> s=t.poll(1000);





		ConsumerRecord<String, String> received = KafkaTestUtils.getSingleRecord(t, "topic");








	}

}
