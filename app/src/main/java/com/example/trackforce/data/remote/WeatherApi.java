package com.example.trackforce.data.remote;

import com.example.trackforce.data.remote.models.WeatherResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather")
    Single<WeatherResponse> getWeather(@Query("lat") double latitude, @Query("lon") double longitude);
}
