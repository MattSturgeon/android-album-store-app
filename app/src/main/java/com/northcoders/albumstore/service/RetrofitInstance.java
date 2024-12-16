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
        return new OkHttpClient.Builder()
                .addInterceptor(createLoggingInterceptor())
                .build();
    }

    private static HttpLoggingInterceptor createLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        logging.redactHeader("Authorization");
        logging.redactHeader("Cookie");
        return logging;
    }

    private static GsonConverterFactory createGsonFactory() {
        return GsonConverterFactory.create();
    }
}
