package com.northcoders.albumstore.ui.bindings;

import static java.util.stream.Collectors.toList;

import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

import com.northcoders.albumstore.model.Artist;

import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AlbumBindingAdapters {
    private static final String TAG = AlbumBindingAdapters.class.getSimpleName();
    private static final Pattern SUFFIX_PATTERN = Pattern.compile("\\s*;?\\s*$");

    @BindingAdapter("android:text")
    public static void setArtistText(TextView view, @Nullable List<Artist> artists) {
        String oldText = view.getText().toString();
        String newText = artistsToPrettyString(artists);

        // Avoid inf-rec
        if (Objects.equals(oldText, newText)) {
            return;
        }

        view.setText(newText);
    }

    @BindingAdapter("android:text")
    public static void setArtistText(EditText view, @Nullable List<Artist> artists) {
        String oldText = view.getText().toString();
        String newText = artistsToString(artists);

        // Avoid stripping trailing deliminator
        Matcher m = SUFFIX_PATTERN.matcher(oldText);
        String suffix = m.find() ? m.group() : "";

        // Avoid inf-rec
        if (Objects.equals(oldText, newText) || Objects.equals(oldText, newText + suffix)) {
            return;
        }

        view.setText(newText);
        view.setSelection(newText.length());
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static @Nullable List<Artist> getArtistText(EditText view) {
        return stringToArtists(view.getText().toString());
    }

    private static String artistsToPrettyString(@Nullable List<Artist> artists) {
        if (artists == null) {
            return "";
        }
        // TODO: implement oxford comma in a string utils class
        String deliminator = artists.size() == 2 ? " and " : ", ";
        return artists.stream()
                .map(Artist::getName)
                .collect(Collectors.joining(deliminator));
    }

    private static String artistsToString(@Nullable List<Artist> artists) {
        if (artists == null) {
            return "";
        }
        return artists.stream()
                .map(Artist::getName)
                .collect(Collectors.joining("; "));
    }

    @Contract("null->null")
    private static @Nullable List<Artist> stringToArtists(@Nullable String artists) {
        if (artists == null || artists.isBlank()) {
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