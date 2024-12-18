package com.northcoders.albumstore.ui.bindings;

import static java.util.stream.Collectors.toList;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

import com.bumptech.glide.Glide;
import com.northcoders.albumstore.model.Artist;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumBindingAdapters {
    private static final String TAG = AlbumBindingAdapters.class.getSimpleName();

    @BindingAdapter("android:text")
    public static void setArtistText(TextView view, List<Artist> artists) {
        view.setText(artistsToString(artists));
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static List<Artist> readArtistText(TextView view) {
        return stringToArtists(view.getText().toString());
    }

    @BindingAdapter(value = {"loadImage", "fallbackImage"}, requireAll = false)
    public static void getImageUrl(ImageView view, String url, Drawable fallbackImage) {
        if (url != null) {
            Glide.with(view.getContext())
                    .load(url)
                    .into(view);
        } else if (fallbackImage != null) {
            view.setImageDrawable(fallbackImage);
        } else {
            Log.w(TAG, "attempted to load image with no url or fallback");
        }
    }

    private static String artistsToString(List<Artist> artists) {
        // TODO: implement oxford comma in a string utils class
        String deliminator = artists.size() == 2 ? " and " : ", ";
        return artists.stream()
                .map(Artist::getName)
                .collect(Collectors.joining(deliminator));
    }

    private static List<Artist> stringToArtists(String artists) {
        // FIXME: allow escaping ';'
        return Arrays.stream(artists.split(";"))
                .map(String::trim)
                .map(name -> new Artist(null, name))
                .collect(toList());
    }
}