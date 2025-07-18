package com.example.trackforce.data.local.mapper;

import com.example.trackforce.data.local.model.WeatherEntity;
import com.example.trackforce.data.remote.models.WeatherResponse;

public class WeatherMapper {

    public static WeatherEntity fromResponse(WeatherResponse response) {
        WeatherEntity entity = new WeatherEntity();
        entity.id = response.getId();
        entity.name = response.getName();
        entity.base = response.getBase();
        entity.cod = response.getCod();
        entity.dt = response.getDt();
        entity.timezone = response.getTimezone();
        entity.visibility = response.getVisibility();
        entity.coord = response.getCoord();
        entity.main = response.getMain();
        entity.clouds = response.getClouds();
        entity.sys = response.getSys();
        entity.wind = response.getWind();
        entity.weather = response.getWeather();
        return entity;
    }
}