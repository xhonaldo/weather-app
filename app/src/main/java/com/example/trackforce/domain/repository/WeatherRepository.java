package com.example.trackforce.domain.repository;

import com.example.trackforce.data.remote.models.WeatherResponse;

import io.reactivex.rxjava3.core.Single;

public interface WeatherRepository {
    Single<WeatherResponse> getWeather(double latitude, double longitude);
}
