package com.northcoders.albumstore.ui.mainactivity;

import android.app.Application;
import android.widget.Spinner;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.northcoders.albumstore.model.Album;
import com.northcoders.albumstore.model.AlbumRepository;
import com.northcoders.albumstore.model.AlbumRequestDTO;
import com.northcoders.albumstore.model.Genre;
import com.northcoders.albumstore.model.GenreRepository;
import com.northcoders.albumstore.ui.genres.GenreAdapter;
import com.northcoders.albumstore.ui.genres.GenreListenerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class MainActivityViewModel extends AndroidViewModel {

    private final AlbumRepository albumRepository;
    private final GenreRepository genreRepository;

    public MainActivityViewModel(@NonNull Application application) {
        this(application, new AlbumRepository(application), new GenreRepository(application));
    }

    public MainActivityViewModel(@NonNull Application application, @NonNull AlbumRepository albumRepository, @NonNull GenreRepository genreRepository) {
        super(application);
        this.albumRepository = albumRepository;
        this.genreRepository = genreRepository;
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

    public LiveData<Map<String, Genre>> getGenres() {
        return genreRepository.getGenres();
    }

    public void initGenreSpinner(ComponentActivity activity, int spinnerViewId, @Nullable String initialGenreKey, Consumer<Genre> setGenreCallback) {
        GenreListenerFactory genreListenerFactory = new GenreListenerFactory(setGenreCallback);
        Spinner view = activity.findViewById(spinnerViewId);
        getGenres().observe(activity, genres -> {
            view.setAdapter(new GenreAdapter(activity, genres));
            // Initial value
            Optional.ofNullable(initialGenreKey)
                    .map(genre -> getGenreId(genre, genres.values()))
                    .ifPresent(genre -> view.setSelection(genre.intValue()));
            view.setOnItemSelectedListener(genreListenerFactory.forGenres(genres));
        });
    }

    private static long getGenreId(String key, Collection<Genre> genres) {
        return genres.stream()
                .filter(genre -> genre.getKey().equals(key))
                .mapToLong(Genre::getId)
                .findAny()
                .orElseThrow();
    }
}
