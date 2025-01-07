package com.northcoders.albumstore.ui.library;

import com.northcoders.albumstore.model.Album;

@FunctionalInterface
public interface AlbumClickHandler {

    void onClickAlbum(Album album);
}
