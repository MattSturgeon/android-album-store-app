package com.northcoders.albumstore.model;

import androidx.annotation.Nullable;

public class Artist {
    @Nullable
    private Long id;
    @Nullable
    private String name;

    public Artist(@Nullable Long id, @Nullable String name) {
        this.id = id;
        this.name = name;
    }

    public @Nullable Long getId() {
        return id;
    }

    public @Nullable String getName() {
        return name;
    }
}
