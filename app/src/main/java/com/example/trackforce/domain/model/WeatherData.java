package com.example.trackforce.domain.model;


public class WeatherData {

    private final String description;

    private final String icon;

    private final int id;

    private final String main;

    private final String logoUrl;

    public WeatherData(String description, String icon, int id, String main, String logoUrl) {
        this.description = description;
        this.icon = icon;
        this.id = id;
        this.main = main;
        this.logoUrl = logoUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getIcon() {
        return icon;
    }

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }
}