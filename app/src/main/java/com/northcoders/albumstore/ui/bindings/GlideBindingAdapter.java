package com.northcoders.albumstore.ui.bindings;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class GlideBindingAdapter {
    private static final String TAG = "GlideBindingAdapter";

    @BindingAdapter(value = {"loadImage", "fallbackImage"}, requireAll = false)
    public static void getImageUrl(ImageView view, @Nullable String url, @Nullable Drawable fallbackImage) {
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
}