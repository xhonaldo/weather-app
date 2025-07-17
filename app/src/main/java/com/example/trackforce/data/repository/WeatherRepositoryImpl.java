package com.example.trackforce.data.repository;

import com.example.trackforce.data.remote.ApiServiceFactory;
import com.example.trackforce.data.remote.WeatherApi;
import com.example.trackforce.data.remote.WeatherRetrofit;
import com.example.trackforce.data.remote.models.WeatherResponse;
import com.example.trackforce.domain.repository.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeatherRepositoryImpl implements WeatherRepository {

        private final WeatherApi weatherApi;

        @Inject
        public WeatherRepositoryImpl(@WeatherRetrofit ApiServiceFactory apiServiceFactory) {
            this.weatherApi = apiServiceFactory.create(WeatherApi.class);
        }

        @Override
        public Single<WeatherResponse> getWeather(double latitude, double longitude) {
            return weatherApi.getWeather(latitude, longitude)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }

}
