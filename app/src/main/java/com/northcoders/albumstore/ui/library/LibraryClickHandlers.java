package com.northcoders.albumstore.ui.library;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.northcoders.albumstore.model.Album;
import com.northcoders.albumstore.ui.updatealbum.UpdateAlbumActivity;

public class LibraryClickHandlers {

    private static final String TAG = "LibraryClickHandlers";

    private final Context context;

    public LibraryClickHandlers(Context context) {
        this.context = context;
    }

    public void onClickAlbum(Album album) {
        Log.i(TAG, String.format("Clicked on \"%s\" (id %d)", album.getTitle(), album.getId()));
        Intent intent = new Intent(context, UpdateAlbumActivity.class);
        intent.putExtra("album", album);
        context.startActivity(intent);
    }
}
