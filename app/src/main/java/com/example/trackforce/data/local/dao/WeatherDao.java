package com.example.trackforce.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.trackforce.data.local.model.model.WeatherEntity;

@Dao
public interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeather(WeatherEntity weatherEntity);

    @Query("SELECT * FROM weather WHERE id = :id")
    LiveData<WeatherEntity> getWeatherById(int id);

    @Query("DELETE FROM weather")
    void clearAll();
}
