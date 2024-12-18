package com.northcoders.albumstore.ui.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.northcoders.albumstore.ui.addalbum.AddNewAlbumActivity;

public class MainActivityClickHandlers {

    private static final String TAG = "MainActivityClickHandlers";

    private final Context context;

    public MainActivityClickHandlers(Context context) {
        this.context = context;
    }

    public void onNewAlbum(View view) {
        Log.i(TAG, "Clicked on NewAlbum, starting activity");
        Intent intent = new Intent(context, AddNewAlbumActivity.class);
        context.startActivity(intent);
    }
}
