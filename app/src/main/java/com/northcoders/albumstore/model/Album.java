package com.northcoders.albumstore.model;

import static java.util.stream.Collectors.toList;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

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
