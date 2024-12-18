package com.northcoders.albumstore.ui.bindings;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

import java.time.Year;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class TimeBindingAdapters {

    private static final String TAG = "TimeBindingAdapters";

    @BindingAdapter("android:text")
    public static void setReleasedText(TextView view, @Nullable Year year) {
        String string = Optional.ofNullable(year).map(Year::toString).orElse(null);
        view.setText(string);
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static @Nullable Year readReleasedText(TextView view) {
        String string = view.getText().toString();
        if (string.isBlank()) {
            return null;
        }
        try {
            return Year.parse(string);
        } catch (DateTimeParseException e) {
            Log.w(TAG, Optional.ofNullable(e.getMessage()).orElse("Unknown error"));
            return null;
        }
    };
}
