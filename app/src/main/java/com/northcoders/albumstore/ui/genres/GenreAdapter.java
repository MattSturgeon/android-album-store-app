package com.northcoders.albumstore.ui.genres;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.northcoders.albumstore.model.Genre;

import java.util.List;
import java.util.Map;

public class GenreAdapter extends ArrayAdapter<Genre> {

    public GenreAdapter(@NonNull Context context, @NonNull List<Genre> genres) {
        super(context, android.R.layout.simple_spinner_dropdown_item, genres);
    }

    public GenreAdapter(@NonNull Context context, @NonNull Map<String, Genre> genres) {
        this(context, getGenreList(genres));
    }

    private static List<Genre> getGenreList(Map<?, Genre> genres) {
         return genres.values().stream()
                .sorted(comparing(Genre::getId))
                .collect(toList());
    }
}
