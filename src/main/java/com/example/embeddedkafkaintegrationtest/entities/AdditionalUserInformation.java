package com.example.embeddedkafkaintegrationtest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AdditionalUserInformation {

    @Id
    private String customerNumber;

    private String additionalInformation;

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
