package com.northcoders.albumstore.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Artist implements Cloneable, Parcelable {

    @Nullable
    private Long id;
    @Nullable
    private String name;

    public Artist(@Nullable Long id, @Nullable String name) {
        this.id = id;
        this.name = name;
    }

    protected Artist(Parcel in) {
        id = in.readByte() == 0
                ? null
                : in.readLong();
        name = in.readString();
    }

    public @Nullable Long getId() {
        return id;
    }

    public @Nullable String getName() {
        return name;
    }

    @Override
    public @NonNull Artist clone() {
        return new Artist(getId(), getName());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeValue(getId());
        dest.writeString(getName());
    }

    public static final Creator<Artist> CREATOR = new Creator<>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };
}
