package com.example.trackforce.domain.usecases;

import com.example.trackforce.data.remote.models.WeatherResponse;
import com.example.trackforce.domain.repository.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetWeatherUseCase {

    private final WeatherRepository repository;

    @Inject
    public GetWeatherUseCase(WeatherRepository repository) {
        this.repository = repository;
    }

    public Single<WeatherResponse> execute(double latitude, double longitude) {
        return repository.getWeather(latitude, longitude);
    }
}