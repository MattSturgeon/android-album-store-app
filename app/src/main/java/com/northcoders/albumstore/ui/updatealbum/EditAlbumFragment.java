package com.northcoders.albumstore.ui.updatealbum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.albumstore.databinding.FragmentEditAlbumBinding;

public class EditAlbumFragment extends Fragment {

    private EditAlbumViewModel mViewModel;
    private FragmentEditAlbumBinding binding;

    public static EditAlbumFragment newInstance() {
        return new EditAlbumFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditAlbumBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EditAlbumViewModel.class);
    }

}