package com.example.trackforce.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.trackforce.data.local.dao.WeatherDao;
import com.example.trackforce.data.local.model.model.WeatherEntity;

@Database(entities = {WeatherEntity.class}, version = 1)
@TypeConverters({WeatherListConverter.class})

public abstract class AppDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();

}