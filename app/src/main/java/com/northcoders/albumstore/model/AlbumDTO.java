package com.northcoders.albumstore.model;

import java.util.List;

public class AlbumDTO {
    private long id;
    private String title;
    private String genre;
    // TODO: store as java.time.Year
    // Need a JsonSerializer/JsonDeserializer type adapter
    // or maybe an InstanceCreator
    private String released;
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

    public String getReleased() {
        return released;
    }

    public long getQuantity() {
        return quantity;
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }
}
