package com.northcoders.albumstore.model;

import static java.util.stream.Collectors.toList;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Album extends BaseObservable implements Cloneable, Parcelable {

    private long id;
    @Nullable
    private String title;
    @Nullable
    private String genre;
    private Year released;
    private long quantity;
    @Nullable
    @SerializedName("album_art_url")
    private String albumArtUrl;
    private List<Artist> artists;

    public Album() {}

    protected Album(Parcel in) {
        id = in.readLong();
        title = in.readString();
        genre = in.readString();
        released = Year.of(in.readInt());
        quantity = in.readLong();
        albumArtUrl = in.readString();
        artists = Optional.ofNullable(
                in.createTypedArrayList(Artist.CREATOR)
        ).orElseGet(ArrayList::new);
    }

    public long getId() {
        return id;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public String getGenre() {
        return genre;
    }

    @Bindable
    public Year getReleased() {
        return released;
    }

    @Bindable
    public long getQuantity() {
        return quantity;
    }

    @Bindable
    public String getAlbumArtUrl() {
        return albumArtUrl;
    }

    @Bindable
    public List<Artist> getArtists() {
        return artists;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public void setGenre(String genre) {
        this.genre = genre;
        notifyPropertyChanged(BR.genre);
    }

    public void setReleased(Year released) {
        this.released = released;
        notifyPropertyChanged(BR.released);
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
        notifyPropertyChanged(BR.quantity);
    }

    public void setAlbumArtUrl(String albumArtUrl) {
        this.albumArtUrl = albumArtUrl;
        notifyPropertyChanged(BR.albumArtUrl);
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
        notifyPropertyChanged(BR.artists);
    }

    @Override
    public @NonNull Album clone() {
        try {
            Album clone = (Album) super.clone();
            clone.id = getId();
            clone.title = getTitle();
            clone.genre = getGenre();
            clone.released = getReleased();
            clone.quantity = getQuantity();
            clone.albumArtUrl = getAlbumArtUrl();
            clone.artists = getArtists().stream().map(Artist::clone).collect(toList());
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
        dest.writeLong(getId());
        dest.writeString(getTitle());
        dest.writeString(getGenre());
        dest.writeInt(getReleased().getValue());
        dest.writeLong(getQuantity());
        dest.writeString(getAlbumArtUrl());
        if (VERSION.SDK_INT >= VERSION_CODES.UPSIDE_DOWN_CAKE) {
            dest.writeTypedList(getArtists(), 0);
        } else {
            //noinspection SimplifyStreamApiCallChains
            dest.writeTypedArray(getArtists().stream().toArray(Artist[]::new), 0);
        }
    }

    public static final Creator<Album> CREATOR = new Creator<>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
}
