package com.example.trackforce.domain.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ErrorHandler {
    public static String getErrorMessage(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            int code = httpException.code();
            String message = parseHttpError(httpException);

            switch (code) {
                case 400:
                    return message != null ? message : "Bad Request.";
                case 401:
                    return message != null ? message : "Unauthorized. Please login again.";
                case 403:
                    return message != null ? message : "Access denied.";
                case 404:
                    return message != null ? message : "Resource not found.";
                case 500:
                    return "Server error. Please try again later.";
                default:
                    return message != null ? message : "Unexpected error occurred. Code: " + code;
            }
        } else if (throwable instanceof IOException) {
            return "No internet connection. Please check your network.";
        } else {
            return "An unexpected error occurred: " + throwable.getLocalizedMessage();
        }
    }

    private static String parseHttpError(HttpException httpException) {
        try {
            ResponseBody errorBody = httpException.response().errorBody();
            if (errorBody != null) {
                String json = errorBody.string();

                JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

                if (jsonObject.has("error")) {
                    return jsonObject.get("error").getAsString();
                } else if (jsonObject.has("message")) {
                    return jsonObject.get("message").getAsString();
                } else if (jsonObject.has("detail")) {
                    return jsonObject.get("detail").getAsString();
                }
            }
        } catch (Exception e) {
            // ignore and fallback
        }
        return null;
    }
}