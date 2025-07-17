package com.example.trackforce.presentation.util;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.core.content.ContextCompat;

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
}
