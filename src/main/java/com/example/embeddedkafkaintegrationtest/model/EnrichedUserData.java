package com.example.embeddedkafkaintegrationtest.model;

public class EnrichedUserData extends UserData{

    private String enrichedInfo;

    public EnrichedUserData()
    {

    }

    public EnrichedUserData(String userName, String customerNumber, String enrichedInfo) {
        super(userName, customerNumber);
        this.enrichedInfo = enrichedInfo;
    }

    public String getEnrichedInfo() {
        return enrichedInfo;
    }

    public void setEnrichedInfo(String enrichedInfo) {
        this.enrichedInfo = enrichedInfo;
    }
}
