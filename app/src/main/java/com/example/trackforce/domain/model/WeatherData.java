package com.example.trackforce.domain.model;


public class WeatherData {

    private final String description;

    private final String icon;

    private final int id;

    private final String main;

    public WeatherData(String description, String icon, int id, String main) {
        this.description = description;
        this.icon = icon;
        this.id = id;
        this.main = main;
    }

    public String getDescription() {
        return description;
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