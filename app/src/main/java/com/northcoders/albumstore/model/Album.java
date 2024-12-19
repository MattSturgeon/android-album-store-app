package com.northcoders.albumstore.model;

import static java.util.stream.Collectors.toList;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;

import java.time.Year;
import java.util.List;

public class Album extends BaseObservable implements Cloneable {
    private long id;
    private String title;
    private String genre;
    private Year released;
    private long quantity;
    @SerializedName("album_art_url")
    private String albumArtUrl;
    private List<Artist> artists;

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
}
