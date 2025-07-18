package com.example.trackforce.domain.model;


import com.example.trackforce.data.remote.models.Wind;

import java.util.List;

public class WeatherResponseData {
    private final String cityName;
    private final String formattedDateTime;
    private final String timezone;
    private final String visibility;
    private final int cod;
    private final CoordData coord;
    private final MainData main;
    private final CloudData cloud;
    private final SysData sys;
    private final List<WeatherData> weatherDataList;
    private final WindData wind;

    public WeatherResponseData(
            String cityName,
            String formattedDateTime,
            String timezone,
            String visibility,
            int cod,
            CoordData coord,
            MainData main,
            CloudData cloud,
            SysData sys,
            List<WeatherData> weatherDataList,
            WindData wind
    ) {
        this.cityName = cityName;
        this.formattedDateTime = formattedDateTime;
        this.timezone = timezone;
        this.visibility = visibility;
        this.cod = cod;
        this.coord = coord;
        this.main = main;
        this.cloud = cloud;
        this.sys = sys;
        this.weatherDataList = weatherDataList;
        this.wind = wind;
    }

    public String getCityName() {
        return cityName;
    }

    public String getFormattedDateTime() {
        return formattedDateTime;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getVisibility() {
        return visibility;
    }

    public int getCod() {
        return cod;
    }

    public CoordData getCoord() {
        return coord;
    }

    public MainData getMain() {
        return main;
    }

    public CloudData getCloud() {
        return cloud;
    }

    public SysData getSys() {
        return sys;
    }

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }

    public WindData getWind() {
        return wind;
    }
}
