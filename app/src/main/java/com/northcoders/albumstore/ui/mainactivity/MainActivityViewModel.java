package com.northcoders.albumstore.ui.mainactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.northcoders.albumstore.model.Album;
import com.northcoders.albumstore.model.AlbumRepository;
import com.northcoders.albumstore.model.AlbumRequestDTO;

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

    public LiveData<List<Album>> getAlbums() {
        return albumRepository.getAlbums();
    }

    public void addAlbum(AlbumRequestDTO album) {
        albumRepository.addAlbum(album);
    }

    public void updateAlbum(Album album) {
        albumRepository.updateAlbum(album);
    }

    public void updateAlbum(long id, AlbumRequestDTO album) {
        albumRepository.updateAlbum(id, album);
    }

    public void removeAlbum(long id) {
        albumRepository.removeAlbum(id);
    }

    public void removeAlbum(Album album) {
        albumRepository.removeAlbum(album);
    }
}
