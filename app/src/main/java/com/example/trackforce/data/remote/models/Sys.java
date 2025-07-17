package com.example.trackforce.data.remote.models;


public class Sys {

    private String country;

    private int id;

    private int sunrise;

    private int sunset;

    private int type;

    public Sys(String country, int id, int sunrise, int sunset, int type) {
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

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public int getType() {
        return type;
    }
}