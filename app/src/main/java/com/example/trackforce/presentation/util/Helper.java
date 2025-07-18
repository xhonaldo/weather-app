package com.example.trackforce.presentation.util;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.core.content.ContextCompat;

import com.example.trackforce.domain.model.WeatherData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Helper {
    @Inject
    public Helper() {

    }

    public void openSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ContextCompat.startActivity(context, intent, null);
    }

    public String jsonPrettyPrinting(String rawJson) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(rawJson);
        return gson.toJson(je);
    }

    public String getBackgroundDrawableName(long sunrise, long sunset, List<WeatherData> weatherList, long currentTimeSec) {
        final int TRANSITION_BUFFER = 15 * 60;

        if (currentTimeSec >= (sunrise - TRANSITION_BUFFER) && currentTimeSec <= (sunrise + TRANSITION_BUFFER)) {
            return "bg_sunrise";
        } else if (currentTimeSec >= (sunset - TRANSITION_BUFFER) && currentTimeSec <= (sunset + TRANSITION_BUFFER)) {
            return "bg_sunset";
        }

        boolean isDay = currentTimeSec >= sunrise && currentTimeSec < sunset;

        String weatherMain = "clear";
        if (weatherList != null && !weatherList.isEmpty() && weatherList.get(0).getMain() != null) {
            weatherMain = weatherList.get(0).getMain();
        }

        String weatherKey = mapWeatherToKey(weatherMain.toLowerCase());

        return "bg_" + weatherKey + "_" + (isDay ? "day" : "night");
    }

    private static String mapWeatherToKey(String weatherMain) {
        if (weatherMain == null) return "clear";

        switch (weatherMain.toLowerCase()) {
            case "rain":
            case "drizzle":
            case "thunderstorm":
                return "rain";
            case "snow":
                return "snow";
            case "clouds":
                return "clouds";
            case "clear":
            default:
                return "clear";
        }
    }
}
