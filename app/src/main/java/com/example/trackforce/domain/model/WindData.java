package com.example.trackforce.domain.model;

public class WindData {

    private final int deg;

    private final String speed;

    public WindData(int deg, String speed) {
        this.deg = deg;
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public String getSpeed() {
        return speed;
    }
}