package com.northcoders.albumstore.model;

import java.time.Year;
import java.util.List;

public class AlbumDTO {
    private long id;
    private String title;
    private String genre;
    private Year released;
    private long quantity;
    private List<ArtistDTO> artists;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Year getReleased() {
        return released;
    }

    public long getQuantity() {
        return quantity;
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }
}
