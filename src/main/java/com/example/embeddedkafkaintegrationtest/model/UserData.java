package com.example.embeddedkafkaintegrationtest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserData {

    private String userName;
    private String customerNumber;
}
