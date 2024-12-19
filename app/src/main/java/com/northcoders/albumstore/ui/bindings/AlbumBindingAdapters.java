package com.northcoders.albumstore.ui.bindings;

import static java.util.stream.Collectors.toList;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

import com.northcoders.albumstore.model.Artist;

import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumBindingAdapters {
    private static final String TAG = AlbumBindingAdapters.class.getSimpleName();

    @BindingAdapter("android:text")
    public static void setArtistText(TextView view, @Nullable List<Artist> artists) {
        view.setText(artistsToString(artists));
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static @Nullable List<Artist> readArtistText(TextView view) {
        return stringToArtists(view.getText().toString());
    }

    @Contract("null->null")
    private static @Nullable String artistsToString(@Nullable List<Artist> artists) {
        if (artists == null) {
            return null;
        }
        // TODO: implement oxford comma in a string utils class
        String deliminator = artists.size() == 2 ? " and " : ", ";
        return artists.stream()
                .map(Artist::getName)
                .collect(Collectors.joining(deliminator));
    }

    @Contract("null->null")
    private static @Nullable List<Artist> stringToArtists(@Nullable String artists) {
        if (artists == null) {
            return null;
        }
        // FIXME: allow escaping ';'
        return Arrays.stream(artists.split(";"))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(name -> new Artist(null, name))
                .collect(toList());
    }
}