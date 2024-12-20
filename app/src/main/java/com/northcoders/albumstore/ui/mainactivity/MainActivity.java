package com.northcoders.albumstore.ui.mainactivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.northcoders.albumstore.R;
import com.northcoders.albumstore.databinding.ActivityMainBinding;
import com.northcoders.albumstore.model.Album;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityClickHandlers clickHandler;
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private List<Album> albums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        clickHandler = new MainActivityClickHandlers(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setClickHandler(clickHandler);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        SearchView searchBar = findViewById(R.id.search_bar);
        searchBar.setOnQueryTextListener(new SearchHandler(this));

        getAllAlbums();
        // TODO: add a way to "refresh" albums
    }

    private void getAllAlbums() {
        viewModel.getAlbums().observe(this, newAlbums -> {
            albums = newAlbums;
            displayInRecyclerView();
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void displayInRecyclerView() {
        recyclerView = binding.recyclerView;
        AlbumAdapter albumAdapter = new AlbumAdapter(albums, clickHandler);
        recyclerView.setAdapter(albumAdapter);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this, FlexDirection.ROW);
        recyclerView.setLayoutManager(flexboxLayoutManager);
        albumAdapter.notifyDataSetChanged();
    }
}