package com.northcoders.albumstore.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

public class Genre extends BaseObservable implements Cloneable, Parcelable {
    private long id;
    private String key;
    @SerializedName("display_name")
    private String displayName;

    protected Genre(Parcel in) {
        id = in.readLong();
        key = in.readString();
        displayName = in.readString();
    }

    @Bindable
    public long getId() {
        return id;
    }

    @Bindable
    public String getKey() {
        return key;
    }

    @Bindable
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public @NonNull String toString() {
        return getDisplayName();
    }

    @Override
    public @NonNull Genre clone() {
        try {
            Genre clone = (Genre) super.clone();
            clone.id = id;
            clone.key = key;
            clone.displayName = displayName;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(key);
        dest.writeString(displayName);
    }

    public static final Creator<Genre> CREATOR = new Creator<>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };
}
