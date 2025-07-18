package com.example.trackforce.domain.model;

public class MainData {
    private final String temp;
    private final String feelsLike;
    private final String tempMin;
    private final String tempMax;
    private final String pressure;
    private final String humidity;
    private final String seaLevel;
    private final String grndLevel;

    public MainData(String temp, String feelsLike, String tempMin, String tempMax, String pressure, String humidity, String seaLevel, String grndLevel) {
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
        this.seaLevel = seaLevel;
        this.grndLevel = grndLevel;
    }

    public String getTemp() {
        return temp;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getTempMin() {
        return tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getSeaLevel() {
        return seaLevel;
    }

    public String getGrndLevel() {
        return grndLevel;
    }
}