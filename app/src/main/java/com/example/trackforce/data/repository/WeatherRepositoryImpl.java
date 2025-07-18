package com.example.trackforce.data.repository;

import com.example.trackforce.data.local.AppDatabase;
import com.example.trackforce.data.local.dao.WeatherDao;
import com.example.trackforce.data.local.mapper.WeatherMapper;
import com.example.trackforce.data.local.model.WeatherEntity;
import com.example.trackforce.data.remote.ApiServiceFactory;
import com.example.trackforce.data.remote.WeatherApi;
import com.example.trackforce.data.remote.WeatherRetrofit;
import com.example.trackforce.data.remote.models.WeatherResponse;
import com.example.trackforce.domain.repository.WeatherRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Singleton
public class WeatherRepositoryImpl extends BaseRepository implements WeatherRepository {

        private final WeatherApi weatherApi;
        private final WeatherDao weatherDao;

        @Inject
        public WeatherRepositoryImpl(
                @WeatherRetrofit ApiServiceFactory apiServiceFactory,
                AppDatabase appDatabase
        ) {
            this.weatherApi = apiServiceFactory.create(WeatherApi.class);
            this.weatherDao = appDatabase.weatherDao();
        }

    @Override
    public Single<WeatherResponse> getWeather(double latitude, double longitude) {
        return weatherApi.getWeather(latitude, longitude)
                .doOnSuccess(response -> {
                    WeatherEntity entity = WeatherMapper.fromResponse(response);
                    entity.id = 0;
                    weatherDao.insertWeather(entity);
                })
                .subscribeOn(Schedulers.io())
                .onErrorResumeNext(throwable -> Single.fromCallable(() -> {
                    try {
                        WeatherEntity entity = getValueBlocking(weatherDao.getWeather(), 2000);
                        if (entity == null) {
                            throw new IllegalStateException("No cached weather data available");
                        }
                        return WeatherMapper.toResponse(entity);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to load cached weather", e);
                    }
                }).subscribeOn(Schedulers.io()))
                .observeOn(AndroidSchedulers.mainThread());
    }
}
