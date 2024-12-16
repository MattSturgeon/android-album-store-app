package com.northcoders.albumstore.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Year;

import retrofit2.converter.gson.GsonConverterFactory;

public class GsonConfig {

    private static Gson instance;

    private GsonConfig() {}

    public static Gson getInstance() {
        if (instance == null) {
            instance = buildGson();
        }
        return instance;
    }

    public static GsonConverterFactory converterFactory() {
        return GsonConverterFactory.create(getInstance());
    }

    private static Gson buildGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Year.class, new YearTypeAdapter())
                .create();
    }
}
