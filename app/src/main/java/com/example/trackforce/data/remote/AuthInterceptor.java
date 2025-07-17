package com.example.trackforce.data.remote;

import androidx.annotation.NonNull;

import com.example.trackforce.BuildConfig;

import java.io.IOException;

import jakarta.inject.Inject;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    @Inject
    public AuthInterceptor() {
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();

        HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                .addQueryParameter("appid", BuildConfig.OPEN_WEATHER_API_KEY)
                .build();

        Request newRequest = originalRequest.newBuilder()
                .url(modifiedUrl)
                .build();

        return chain.proceed(newRequest);
    }


}
