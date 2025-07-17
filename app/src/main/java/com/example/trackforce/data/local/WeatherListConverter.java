package com.example.trackforce.data.local;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import com.example.trackforce.data.remote.models.Weather;

import java.util.List;

public class WeatherListConverter {

    private final GenericListConverter<Weather> converter = new GenericListConverter<>(Weather.class);

    @TypeConverter
    public String fromList(List<Weather> list) {
        return converter.fromList(list);
    }

    @TypeConverter
    public List<Weather> fromString(String json) {
        return converter.fromString(json);
    }
}