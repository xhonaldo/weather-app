package com.example.trackforce.data.remote.module;

import com.example.trackforce.data.repository.WeatherRepositoryImpl;
import com.example.trackforce.domain.repository.WeatherRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class WeatherModule {

    @Binds
    @Singleton
    public abstract WeatherRepository bindWeatherRepository(WeatherRepositoryImpl impl);
}