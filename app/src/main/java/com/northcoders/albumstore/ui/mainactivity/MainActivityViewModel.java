package com.northcoders.albumstore.ui.mainactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.northcoders.albumstore.model.AlbumDTO;
import com.northcoders.albumstore.model.AlbumRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private final AlbumRepository albumRepository;

    public MainActivityViewModel(@NonNull Application application) {
        this(application, new AlbumRepository(application));
    }

    public MainActivityViewModel(@NonNull Application application, @NonNull AlbumRepository albumRepository) {
        super(application);
        this.albumRepository = albumRepository;
    }

    public LiveData<List<AlbumDTO>> getAlbums() {
        return albumRepository.getAlbums();
    }
}
