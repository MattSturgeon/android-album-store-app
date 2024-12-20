package com.northcoders.albumstore.ui.genres;

import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;

import com.northcoders.albumstore.model.Genre;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

public class GenreListenerFactory {
    private final Consumer<Genre> setGenreCallback;

    public GenreListenerFactory(Consumer<Genre> setGenreCallback) {
        this.setGenreCallback = setGenreCallback;
    }

    public @NonNull AdapterView.OnItemSelectedListener forGenres(Map<String, Genre> genres) {
        return forGenres(genres.values());
    }

    public @NonNull AdapterView.OnItemSelectedListener forGenres(Collection<Genre> genres) {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genres.stream()
                        .filter(g -> g.getId() == id)
                        .findAny()
                        .ifPresent(setGenreCallback);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
    }
}