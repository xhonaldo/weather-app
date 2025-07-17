package com.example.trackforce.di;

import com.example.trackforce.domain.repository.WeatherRepository;
import com.example.trackforce.domain.usecases.GetWeatherUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {
    @Provides
    public GetWeatherUseCase provideGetWeatherUseCase(WeatherRepository repository) {
        return new GetWeatherUseCase(repository);
    }
}
