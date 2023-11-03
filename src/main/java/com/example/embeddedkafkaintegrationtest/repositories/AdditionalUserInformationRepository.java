package com.example.embeddedkafkaintegrationtest.repositories;

import com.example.embeddedkafkaintegrationtest.entities.AdditionalUserInformation;
import org.springframework.data.repository.CrudRepository;

public interface AdditionalUserInformationRepository extends CrudRepository<AdditionalUserInformation, String> {
}
