package com.example.trackforce.data.local.module;

import android.content.Context;

import androidx.room.Room;

import com.example.trackforce.data.local.AppDatabase;
import com.example.trackforce.data.local.dao.WeatherDao;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public static AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "app_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public static WeatherDao provideWeatherDao(AppDatabase appDatabase) {
        return appDatabase.weatherDao();
    }
}