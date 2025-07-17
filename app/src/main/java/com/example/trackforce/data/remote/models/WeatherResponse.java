package com.example.trackforce.data.remote.models;

import java.util.List;

public class WeatherResponse {
    private String base;
    private Cloud clouds;
    private int cod;
    private Coord coord;
    private int dt;
    private int id;
    private Main main;
    private String name;
    private Sys sys;
    private int timezone;
    private int visibility;
    private List<Weather> weather;
    private Wind wind;

    public WeatherResponse(String base, Cloud clouds, int cod, Coord coord, int dt, int id,
                           Main main, String name, Sys sys, int timezone, int visibility,
                           List<Weather> weather, Wind wind) {
        this.base = base;
        this.clouds = clouds;
        this.cod = cod;
        this.coord = coord;
        this.dt = dt;
        this.id = id;
        this.main = main;
        this.name = name;
        this.sys = sys;
        this.timezone = timezone;
        this.visibility = visibility;
        this.weather = weather;
        this.wind = wind;
    }

    public String getBase() {
        return base;
    }

    public Cloud getClouds() {
        return clouds;
    }

    public int getCod() {
        return cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public int getDt() {
        return dt;
    }

    public int getId() {
        return id;
    }

    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public Sys getSys() {
        return sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public int getVisibility() {
        return visibility;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Wind getWind() {
        return wind;
    }
}