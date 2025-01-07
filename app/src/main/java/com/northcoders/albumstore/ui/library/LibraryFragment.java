package com.northcoders.albumstore.ui.library;

import android.os.Bundle;
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

import java.util.List;

public class LibraryFragment extends Fragment {

    private LibraryClickHandlers clickHandler;
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
        clickHandler = new LibraryClickHandlers(requireActivity());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLibraryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.setClickHandler(clickHandler);
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        getAllAlbums(view);
        return view;
    }

    private void getAllAlbums(View libraryView) {
        viewModel.getAlbums().observe(getViewLifecycleOwner(), newAlbums -> {
            albums = newAlbums;
            albumAdapter = new AlbumAdapter(albums, clickHandler);
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