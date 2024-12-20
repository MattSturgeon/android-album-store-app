package com.northcoders.albumstore.ui.mainactivity;

import static java.util.stream.Collectors.toList;

import android.widget.SearchView;

import androidx.annotation.Nullable;

import com.northcoders.albumstore.model.Album;
import com.northcoders.albumstore.model.Artist;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

class SearchHandler implements SearchView.OnQueryTextListener {

    private final Supplier<List<Album>> albumSupplier;
    private final Consumer<List<Album>> callback;

    private SearchHandler(Supplier<List<Album>> albumSupplier, Consumer<List<Album>> callback) {
        this.albumSupplier = albumSupplier;
        this.callback = callback;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(@Nullable String text) {
        callback.accept(AlbumQuery.of(text).apply(albumSupplier.get()));
        return false;
    }

    @FunctionalInterface
    private interface AlbumQuery {
        List<Album> apply(List<Album> albums);

        static AlbumQuery of(@Nullable String text) {
            // Blank text is a no-op
            if (text == null || text.isBlank()) {
                return albums -> albums;
            }

            // Otherwise use the impl below
            return new AlbumQueryImpl(text.toLowerCase());
        }
    }

    private static class AlbumQueryImpl implements AlbumQuery {
        private final String query;

        private AlbumQueryImpl(String query) {
            this.query = query;
        }

        public List<Album> apply(List<Album> albums) {
            return albums.stream()
                    .filter(this::check)
                    .collect(toList());
        }

        private boolean check(Album album) {
            return checkString(album.getTitle()) || checkArtists(album.getArtists());
        }

        private boolean check(Artist artist) {
            return checkString(artist.getName());
        }

        private boolean checkString(@Nullable String string) {
            return Optional.ofNullable(string)
                    .map(String::toLowerCase)
                    .map(s -> s.contains(query))
                    .orElse(false);
        }

        private boolean checkArtists(@Nullable List<Artist> artists) {
            if (artists == null) {
                return false;
            }

            return artists.stream().anyMatch(this::check);
        }
    }

    static SearchHandlerFactory forAlbums(Supplier<List<Album>> albumSupplier) {
        return new SearchHandlerFactory(albumSupplier);
    }

    static class SearchHandlerFactory {
        private final Supplier<List<Album>> albumSupplier;

        private SearchHandlerFactory(Supplier<List<Album>> albumSupplier) {
            this.albumSupplier = albumSupplier;
        }

        SearchHandler withCallback(Consumer<List<Album>> callback) {
            return new SearchHandler(albumSupplier, callback);
        }
    }
}
