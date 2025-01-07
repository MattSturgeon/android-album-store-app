package com.northcoders.albumstore.ui.addalbum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.albumstore.R;
import com.northcoders.albumstore.databinding.FragmentAddAlbumBinding;
import com.northcoders.albumstore.model.AlbumRequestDTO;
import com.northcoders.albumstore.ui.mainactivity.MainActivityViewModel;

public class AddAlbumFragment extends Fragment {

    private MainActivityViewModel viewModel;
    private FragmentAddAlbumBinding binding;
    private AlbumRequestDTO album;

    public AddAlbumFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        album = new AlbumRequestDTO();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddAlbumBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Spinner spinner = view.findViewById(R.id.new_album_genre);

        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        binding.setClickHandlers(new AddAlbumClickHandlers(viewModel, requireContext(), album));
        binding.setAlbum(album);
        viewModel.initGenreSpinner(
                requireActivity(),
                spinner,
                album.getGenre(),
                genre -> binding.getAlbum().setGenre(genre.getKey())
        );

        return view;
    }
}