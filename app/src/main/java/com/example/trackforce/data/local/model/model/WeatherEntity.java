package com.example.trackforce.data.local.model.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.trackforce.data.local.WeatherListConverter;
import com.example.trackforce.data.remote.models.Cloud;
import com.example.trackforce.data.remote.models.Coord;
import com.example.trackforce.data.remote.models.Main;
import com.example.trackforce.data.remote.models.Sys;
import com.example.trackforce.data.remote.models.Weather;
import com.example.trackforce.data.remote.models.Wind;

import java.util.List;

@Entity(tableName = "weather")
public class WeatherEntity {

    @PrimaryKey
    public int id;

    public String name;
    public String base;
    public int cod;
    public int dt;
    public int timezone;
    public int visibility;

    @Embedded(prefix = "coord_")
    public Coord coord;

    @Embedded(prefix = "main_")
    public Main main;

    @Embedded(prefix = "clouds_")
    public Cloud clouds;

    @Embedded(prefix = "sys_")
    public Sys sys;

    @Embedded(prefix = "wind_")
    public Wind wind;

    @TypeConverters(WeatherListConverter.class)
    public List<Weather> weather;
}
