package com.example.trackforce.data.remote.models;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("feels_like")
    private double feelsLike;

    @SerializedName("grnd_level")
    private int grndLevel;

    private int humidity;

    private int pressure;

    @SerializedName("sea_level")
    private int seaLevel;

    @SerializedName("temp")
    private double temp;

    @SerializedName("temp_max")
    private double tempMax;

    @SerializedName("temp_min")
    private double tempMin;

    public Main(double feelsLike, int grndLevel, int humidity, int pressure, int seaLevel,
                double temp, double tempMax, double tempMin) {
        this.feelsLike = feelsLike;
        this.grndLevel = grndLevel;
        this.humidity = humidity;
        this.pressure = pressure;
        this.seaLevel = seaLevel;
        this.temp = temp;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public int getGrndLevel() {
        return grndLevel;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public int getSeaLevel() {
        return seaLevel;
    }

    public double getTemp() {
        return temp;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }
}