package com.northcoders.albumstore.ui.bindings;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.northcoders.albumstore.model.Artist;

import java.util.List;
import java.util.stream.Collectors;

public class AlbumBindingAdapters {
    @BindingAdapter("android:text")
    public static void setArtistText(TextView view, List<Artist> artists) {
        view.setText(artistsToString(artists));
    }

    @BindingAdapter("loadImage")
    public static void getImageUrl(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }

    private static String artistsToString(List<Artist> artists) {
        // TODO: implement oxford comma in a string utils class
        String deliminator = artists.size() == 2 ? " and " : ", ";
        return artists.stream()
                .map(Artist::getName)
                .collect(Collectors.joining(deliminator));
    }
}