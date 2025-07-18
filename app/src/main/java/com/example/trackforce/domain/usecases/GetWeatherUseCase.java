package com.example.trackforce.domain.usecases;

import com.example.trackforce.domain.mapper.WeatherDomainMapper;
import com.example.trackforce.domain.model.WeatherResponseData;
import com.example.trackforce.domain.repository.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetWeatherUseCase {

    private final WeatherRepository repository;
    private final WeatherDomainMapper mapper;

    @Inject
    public GetWeatherUseCase(WeatherRepository repository, WeatherDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Single<WeatherResponseData> execute(double latitude, double longitude) {
        return repository.getWeather(latitude, longitude)
                .map(mapper::fromResponse);
    }
}