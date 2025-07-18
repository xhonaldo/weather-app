package com.example.trackforce.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.trackforce.data.local.model.WeatherEntity;

@Dao
public interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeather(WeatherEntity weatherEntity);
    @Query("SELECT * FROM weather LIMIT 1")
    LiveData<WeatherEntity> getWeather();
    @Query("DELETE FROM weather")
    void clearAll();
}
