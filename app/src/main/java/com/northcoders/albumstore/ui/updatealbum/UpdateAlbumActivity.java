package com.northcoders.albumstore.ui.updatealbum;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.albumstore.R;
import com.northcoders.albumstore.databinding.ActivityUpdateAlbumBinding;
import com.northcoders.albumstore.model.Album;
import com.northcoders.albumstore.ui.mainactivity.MainActivity;
import com.northcoders.albumstore.ui.mainactivity.MainActivityViewModel;

public class UpdateAlbumActivity extends AppCompatActivity {

    private static final String TAG = "UpdateAlbumActivity";
    private ActivityUpdateAlbumBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_album);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Album album = getAlbum();
        if (album == null) {
            exit("Album was null");
            return;
        }
        Log.d(TAG, String.format("Extracted album from parcel (id: %d)", album.getId()));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_album);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.setAlbum(album);
    }

    @Nullable
    private Album getAlbum() {
        return getFromParcel("album", Album.class);
    }

    @Nullable
    private <T extends Parcelable> T getFromParcel(String name, Class<T> type) {
        if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
            return getIntent().getParcelableExtra(name, type);
        }
        return getIntent().getParcelableExtra(name);
    }

    private void exit(String error) {
        Log.e(TAG, error);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }
}