package com.example.trackforce.di;

import android.content.Context;

import com.example.trackforce.WeatherApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    WeatherApplication provideWeatherApplication(@ApplicationContext Context context) {
        return  ( WeatherApplication ) context;
    }

}
