package com.northcoders.albumstore.ui.bindings;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

import java.time.Year;

public class TimeBindingAdapters {

    @BindingAdapter("android:text")
    public static void setReleasedText(TextView view, Year year) {
        view.setText(year.toString());
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static Year readReleasedText(TextView view) {
        // TODO: does this need exception handling?
        return Year.parse(view.getText().toString());
    }
}
