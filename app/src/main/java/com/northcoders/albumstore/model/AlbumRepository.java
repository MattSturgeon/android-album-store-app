package com.northcoders.albumstore.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.northcoders.albumstore.service.RetrofitInstance;

import java.util.List;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumRepository {

    private static final String TAG = AlbumRepository.class.getSimpleName();
    private final MutableLiveData<List<Album>> albums = new MutableLiveData<>();
    private final Application application;

    public AlbumRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Album>> getAlbums() {
        Callback<List<Album>> callback = new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<Album>> call, @NonNull Response<List<Album>> response) {
                albums.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Album>> call, @NonNull Throwable throwable) {
                Log.e(TAG, Optional.ofNullable(throwable.getMessage()).orElse("Unknown error"));
            }
        };

        RetrofitInstance.getService()
                .listAlbums()
                .enqueue(callback);

        return albums;
    }
}
