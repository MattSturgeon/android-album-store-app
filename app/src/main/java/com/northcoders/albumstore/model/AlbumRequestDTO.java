package com.northcoders.albumstore.model;

import static java.util.stream.Collectors.toList;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;

import java.time.Year;
import java.util.List;

public class AlbumRequestDTO extends BaseObservable {
    private String title;
    private String genre;
    private Year released;
    @Nullable
    @SerializedName("album_art_url")
    private String albumArtUrl;
    private List<Artist> artists;

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
    public String getAlbumArtUrl() {
        return albumArtUrl;
    }

    @Bindable
    public List<Artist> getArtists() {
        return artists;
    }

    @Bindable
    public List<String> getArtistNames() {
        return getArtists().stream()
                .map(Artist::getName)
                .collect(toList());
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

    public void setAlbumArtUrl(@Nullable String albumArtUrl) {
        this.albumArtUrl = albumArtUrl;
        notifyPropertyChanged(BR.albumArtUrl);
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
        notifyPropertyChanged(BR.artists);
    }
}
