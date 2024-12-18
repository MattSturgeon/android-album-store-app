package com.northcoders.albumstore.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.time.Year;
import java.util.List;

public class Album extends BaseObservable {
    private long id;
    private String title;
    private String genre;
    private Year released;
    private long quantity;
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
    public List<Artist> getArtists() {
        return artists;
    }
}
