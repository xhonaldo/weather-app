package com.example.trackforce.domain.model;

public class SysData {
    private final String country;

    private final int id;

    private final String sunrise;

    private final String sunset;

    private final int type;

    public SysData(String country, int id, String sunrise, String sunset, int type) {
        this.country = country;
        this.id = id;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public int getId() {
        return id;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public int getType() {
        return type;
    }
}
