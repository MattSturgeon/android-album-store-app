package com.northcoders.albumstore.ui.library;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.northcoders.albumstore.R;
import com.northcoders.albumstore.databinding.FragmentLibraryBinding;
import com.northcoders.albumstore.model.Album;
import com.northcoders.albumstore.ui.mainactivity.MainActivityViewModel;
import com.northcoders.albumstore.ui.updatealbum.UpdateAlbumActivity;

import java.util.List;

public class LibraryFragment extends Fragment implements AlbumClickHandler {

    private static final String TAG = "LibraryFragment";

    private MainActivityViewModel viewModel;
    private FragmentLibraryBinding binding;
    private RecyclerView recyclerView;
    private AlbumAdapter albumAdapter;
    private List<Album> albums;

    public LibraryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLibraryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        getAllAlbums(view);
        return view;
    }

    @Override
    public void onClickAlbum(Album album) {
        Log.i(TAG, String.format("Clicked on \"%s\" (id %d)", album.getTitle(), album.getId()));
        Intent intent = new Intent(requireContext(), UpdateAlbumActivity.class);
        intent.putExtra("album", album);
        requireContext().startActivity(intent);
    }

    private void getAllAlbums(View libraryView) {
        viewModel.getAlbums().observe(getViewLifecycleOwner(), newAlbums -> {
            albums = newAlbums;
            albumAdapter = new AlbumAdapter(albums, this);
            setupSearch(libraryView);
            displayInRecyclerView();
        });
    }

    private void displayInRecyclerView() {
        recyclerView = binding.recyclerView;
        recyclerView.setAdapter(albumAdapter);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext(), FlexDirection.ROW);
        recyclerView.setLayoutManager(flexboxLayoutManager);
    }

    private void setupSearch(View libraryView) {
        SearchView searchBar = libraryView.findViewById(R.id.search_bar);
        searchBar.setOnQueryTextListener(SearchHandler.forAlbums(() -> albums).withCallback(albums -> albumAdapter.setAlbums(albums)));
    }
}