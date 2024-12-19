package com.northcoders.albumstore.ui.bindings;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

import java.time.Year;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Optional;

public class TimeBindingAdapters {

    private static final String TAG = "TimeBindingAdapters";

    @BindingAdapter("android:text")
    public static void setYear(EditText view, @Nullable Year newValue) {
        String oldText = view.getText().toString();
        String newText = Optional.ofNullable(newValue)
                .map(Year::toString)
                .orElse("");

        // Avoid inf-loop
        if (Objects.equals(oldText, newText)) {
            return;
        }

        view.setText(newText);
        view.setSelection(newText.length());
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static @Nullable Year getYear(TextView view) {
        CharSequence newValue = view.getText();
        try {
            return Year.parse(newValue);
        } catch (DateTimeParseException e) {
            String error = Optional.ofNullable(e.getMessage()).orElse("Unknown error");
            Log.w(TAG, error);
            view.setError(error);
            return null;
        }
    };
}
