package com.example.embeddedkafkaintegrationtest.model;


public class UserData {

    private String userName;
    private String customerNumber;

    public UserData() {

    }


    public UserData(String userName, String customerNumber) {
        this.userName = userName;
        this.customerNumber = customerNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
