package com.example.trackforce.domain.model;

public class CloudData {
    private final String cloudiness;

    public CloudData(String cloudiness) {
        this.cloudiness = cloudiness;
    }

    public String getCloudiness() {
        return cloudiness;
    }
}