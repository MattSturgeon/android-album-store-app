package com.northcoders.albumstore.ui.addalbum;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.northcoders.albumstore.model.AlbumRequestDTO;
import com.northcoders.albumstore.ui.mainactivity.MainActivity;
import com.northcoders.albumstore.ui.mainactivity.MainActivityViewModel;

public class AddAlbumClickHandlers {

    private static final String TAG = "ClickHandlers";
    private AlbumRequestDTO album;
    private Context context;
    private MainActivityViewModel mainActivityViewModel;

    public AddAlbumClickHandlers(MainActivityViewModel mainActivityViewModel, Context context, AlbumRequestDTO album) {
        this.mainActivityViewModel = mainActivityViewModel;
        this.context = context;
        this.album = album;
    }

    public void onSubmit(View view) {
        // TODO: validate album has required fields
        // Show a toast and/or inline validation error

        Log.i(TAG, "tapped submit");
        Intent intent = new Intent(context, MainActivity.class);

        AlbumRequestDTO copy = album.clone();
        mainActivityViewModel.addAlbum(copy);
        context.startActivity(intent);
    }

    public void onCancel(View view) {
        Log.i(TAG, "tapped cancel");
        Toast.makeText(context, "Canceling...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}
