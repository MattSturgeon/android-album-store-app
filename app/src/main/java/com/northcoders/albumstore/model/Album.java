package com.northcoders.albumstore.model;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

public class Album {
    private long id;
    private String title;
    private String genre;
    private Year released;
    private long quantity;
    private List<Artist> artists;

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

    public List<Artist> getArtists() {
        return artists;
    }

    public String getArtistString() {
        // TODO: implement oxford comma in a string utils class
        String deliminator = getArtists().size() == 2 ? "and" : ",";
        return getArtists().stream()
                .map(Artist::getName)
                .collect(Collectors.joining(deliminator));
    }
}
