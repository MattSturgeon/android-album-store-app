package com.northcoders.albumstore.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL = "http://10.0.2.2:8080/api/v1/";

    private static Retrofit instance;

    private RetrofitInstance() {}

    public static AlbumApiService getService() {
        return getInstance().create(AlbumApiService.class);
    }

    private static Retrofit getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    private static Retrofit createInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createClient())
                .addConverterFactory(createGsonFactory())
                .build();
    }

    private static OkHttpClient createClient() {
        return new OkHttpClient.Builder().build();
    }

    private static GsonConverterFactory createGsonFactory() {
        return GsonConverterFactory.create();
    }
}
