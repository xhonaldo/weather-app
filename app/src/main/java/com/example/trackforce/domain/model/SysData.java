package com.example.trackforce.domain.model;

public class SysData {
    private final String country;

    private final int id;

    private final String sunriseDescription;

    private final String sunsetDescription;

    private final int type;

    private final int sunrise;

    private final int sunset;

    public SysData(String country, int id, String sunriseDescription, String sunsetDescription,
                   int type, int sunrise, int sunset
    ) {
        this.country = country;
        this.id = id;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.type = type;
        this.sunsetDescription = sunsetDescription;
        this.sunriseDescription = sunriseDescription;
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
    public String getSunriseDescription() {
        return sunriseDescription;
    }

    public String getSunsetDescription() {
        return sunsetDescription;
    }

    public int getType() {
        return type;
    }
}
