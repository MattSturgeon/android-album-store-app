package com.northcoders.albumstore.ui.addalbum;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.albumstore.R;
import com.northcoders.albumstore.databinding.ActivityAddNewAlbumBinding;
import com.northcoders.albumstore.model.AlbumRequestDTO;
import com.northcoders.albumstore.ui.genres.GenreAdapter;
import com.northcoders.albumstore.ui.genres.GenreListenerFactory;
import com.northcoders.albumstore.ui.mainactivity.MainActivityViewModel;

public class AddNewAlbumActivity extends AppCompatActivity {

    private static final String TAG = "AddNewAlbumActivity";
    private ActivityAddNewAlbumBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_album);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AlbumRequestDTO album = new AlbumRequestDTO();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_album);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.setClickHandlers(new AddAlbumClickHandlers(viewModel, this, album));
        binding.setAlbum(album);

        GenreListenerFactory genreListenerFactory = new GenreListenerFactory(genre -> {
            binding.getAlbum().setGenre(genre.getKey());
            binding.notifyPropertyChanged(BR.genre);
        });

        viewModel.getGenres().observe(this, genres -> {
            Spinner view = findViewById(R.id.new_album_genre);
            GenreAdapter genreAdapter = new GenreAdapter(this, genres);
            view.setAdapter(genreAdapter);
            view.setOnItemSelectedListener(genreListenerFactory.forGenres(genres));
        });
    }
}