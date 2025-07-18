package com.example.trackforce.domain.model;

public class CoordData {
    private final double lat;
    private final double lon;

    public CoordData(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}