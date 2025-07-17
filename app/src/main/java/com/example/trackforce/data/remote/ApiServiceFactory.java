package com.example.trackforce.data.remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

@Singleton
public class ApiServiceFactory {

    private final Retrofit retrofit;

    @Inject
    public ApiServiceFactory(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public <T> T create(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}