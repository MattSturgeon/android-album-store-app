package com.northcoders.albumstore.ui.updatealbum;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.albumstore.R;
import com.northcoders.albumstore.databinding.ActivityUpdateAlbumBinding;
import com.northcoders.albumstore.model.Album;
import com.northcoders.albumstore.model.Genre;
import com.northcoders.albumstore.ui.genres.GenreAdapter;
import com.northcoders.albumstore.ui.genres.GenreListenerFactory;
import com.northcoders.albumstore.ui.mainactivity.MainActivityViewModel;

import java.util.Collection;
import java.util.Optional;

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
            Log.e(TAG, "Album was null");
            finish();
            return;
        }
        Log.d(TAG, String.format("Extracted album from parcel (id: %d)", album.getId()));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_album);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.setClickHandler(new UpdateAlbumClickHandlers(viewModel, this, album));
        binding.setAlbum(album);

        GenreListenerFactory genreListenerFactory = new GenreListenerFactory(genre -> {
            binding.getAlbum().setGenre(genre.getKey());
            binding.notifyPropertyChanged(BR.genre);
        });

        viewModel.getGenres().observe(this, genres -> {
            Spinner view = findViewById(R.id.update_album_genre);
            view.setAdapter(new GenreAdapter(this, genres));
            Optional.ofNullable(album.getGenre())
                    .map(genre -> getGenreId(genre, genres.values()))
                    .ifPresent(genre -> view.setSelection(genre.intValue()));
            view.setOnItemSelectedListener(genreListenerFactory.forGenres(genres));
        });
    }

    private static long getGenreId(String key, Collection<Genre> genres) {
        return genres.stream()
                .filter(genre -> genre.getKey().equals(key))
                .mapToLong(Genre::getId)
                .findAny()
                .orElseThrow();
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
}