package com.example.trackforce.domain.util;

import com.example.trackforce.BuildConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DomainHelper {
    @Inject
    public DomainHelper() {

    }

    public String formatUnixTimestamp(int unixSeconds) {
        Date date = new Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }
    public String formatTimezone(int timezoneOffsetSeconds) {
        int totalMinutes = timezoneOffsetSeconds / 60;
        int hours = totalMinutes / 60;
        int minutes = Math.abs(totalMinutes % 60);
        char sign = hours >= 0 ? '+' : '-';
        int absHours = Math.abs(hours);
        return String.format("GMT%c%02d:%02d", sign, absHours, minutes);
    }

    public String getUtcTimeHms(long timestampSeconds) {
        Date date = new Date(timestampSeconds * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    public String getLogoUrl(String logoCode) {
        return "https://openweathermap.org/img/wn/"+logoCode+"@4x.png";
    }

    public String kelvinToCelsius(double kelvin) {
        double celsius = kelvin - 273.15;
        double rounded = Math.round(celsius * 10.0) / 10.0;
        return addCelsiusMetric(rounded);
    }

    public String addCelsiusMetric(Object value) {
        if (value == null) return "";
        return value + " °C";
    }

    public String addPercentageMetric(Object value) {
        if (value == null) return "";
        return value + "%";
    }

    public String addHectopascalsMetric(Object value) {
        if (value == null) return "";
        return value +" Hpa";
    }

    public String addMeterMetric(Object value) {
        if (value == null) return "";
        return value +" M";
    }

    public String addSpeedMetric(Object value) {
        if (value == null) return "";
        return value +" m/s";
    }
}
