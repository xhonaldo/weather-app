package com.example.trackforce.presentation.util;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

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
}
