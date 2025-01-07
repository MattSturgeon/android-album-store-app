package com.northcoders.albumstore.ui.library;

import static java.util.Collections.emptyList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.albumstore.R;
import com.northcoders.albumstore.databinding.AlbumItemBinding;
import com.northcoders.albumstore.model.Album;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<Album> albums = emptyList();
    private final AlbumClickHandler clickHandler;

    public AlbumAdapter(AlbumClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    public AlbumAdapter(List<Album> albums, AlbumClickHandler clickHandler) {
        this(clickHandler);
        setAlbums(albums);
    }

    @Override
    public @NonNull AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        AlbumItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.album_item,
                parent,
                false
        );
        binding.setClickHandler(clickHandler);
        return new AlbumViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albums.get(position);
        holder.binding.setAlbum(album);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setAlbums(List<Album> albums) {
        this.albums = albums;
        this.notifyDataSetChanged();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        private final AlbumItemBinding binding;

        public AlbumViewHolder(@NonNull AlbumItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
