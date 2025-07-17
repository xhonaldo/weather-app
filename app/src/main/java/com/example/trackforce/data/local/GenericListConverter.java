package com.example.trackforce.data.local;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GenericListConverter<T> {

    private final Gson gson = new Gson();
    private final Class<T> clazz;

    public GenericListConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @TypeConverter
    public String fromList(List<T> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public List<T> fromString(String json) {
        Type type = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(json, type);
    }
}