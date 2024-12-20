package com.northcoders.albumstore.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.northcoders.albumstore.service.RetrofitInstance;

import java.util.Map;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreRepository {

    private static final String TAG = GenreRepository.class.getSimpleName();
    private final MutableLiveData<Map<String, Genre>> genres = new MutableLiveData<>();
    private final Application application;

    public GenreRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<Map<String, Genre>> getGenres() {
        Callback<Map<String, Genre>> callback = new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Map<String, Genre>> call, @NonNull Response<Map<String, Genre>> response) {
                genres.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Map<String, Genre>> call, @NonNull Throwable throwable) {
                Log.e(TAG, Optional.ofNullable(throwable.getMessage()).orElse("Unknown error"));
                Toast.makeText(application, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        };

        RetrofitInstance.getService()
                .listGenres()
                .enqueue(callback);

        return genres;
    }
}
