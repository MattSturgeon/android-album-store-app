package com.northcoders.albumstore.ui.updatealbum;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.northcoders.albumstore.model.Album;
import com.northcoders.albumstore.ui.mainactivity.MainActivity;
import com.northcoders.albumstore.ui.mainactivity.MainActivityViewModel;

public class UpdateAlbumClickHandlers {

    private static final String TAG = "ClickHandlers";
    private Album album;
    private Context context;
    private MainActivityViewModel mainActivityViewModel;

    public UpdateAlbumClickHandlers(MainActivityViewModel mainActivityViewModel, Context context, Album album) {
        this.mainActivityViewModel = mainActivityViewModel;
        this.context = context;
        this.album = album;
    }

    public void onSubmit(View view) {
        // TODO: validate album has required fields
        // Show a toast and/or inline validation error

        Log.i(TAG, "tapped submit");
        Intent intent = new Intent(context, MainActivity.class);

        Album copy = album.clone();
        mainActivityViewModel.updateAlbum(copy);
        context.startActivity(intent);
    }

    public void onRemove(View view) {
        Log.i(TAG, "tapped remove");
        Intent intent = new Intent(context, MainActivity.class);

        Album copy = album.clone();
        mainActivityViewModel.removeAlbum(copy);
        context.startActivity(intent);
    }

    public void onCancel(View view) {
        Log.i(TAG, "tapped cancel");
        Toast.makeText(context, "Canceling...", Toast.LENGTH_SHORT).show();
        context.startActivity(new Intent(context, MainActivity.class));
    }

}
