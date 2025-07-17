package com.example.trackforce;

import androidx.multidex.MultiDexApplication;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class WeatherApplication extends MultiDexApplication {

    private static WeatherApplication mInstance;

    public static WeatherApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

}
