package com.northcoders.albumstore.ui.mainactivity;

import static java.util.stream.Collectors.toList;

import android.widget.SearchView;

import androidx.annotation.Nullable;

import com.northcoders.albumstore.model.Album;
import com.northcoders.albumstore.model.Artist;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

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

            // Separate tokens by whitespace and normalise them to lower case
            // TODO: allow "quoting" tokens that contain whitespace
            List<String> tokens = Optional.ofNullable(text)
                    .map(s -> s.split("\\s+"))
                    .map(Arrays::stream)
                    .orElseGet(Stream::empty)
                    .filter(s -> !s.isEmpty())
                    .map(String::toLowerCase)
                    .collect(toList());

            // Empty tokens is a no-op
            if (tokens.isEmpty()) {
                return albums -> albums;
            }

            // Otherwise use the impl below
            return new AlbumQueryImpl(tokens);
        }
    }

    private static class AlbumQueryImpl implements AlbumQuery {
        private final List<String> tokens;

        private AlbumQueryImpl(List<String> tokens) {
            this.tokens = tokens;
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
                    .map(this::matchesAnyToken)
                    .orElse(false);
        }

        private boolean checkArtists(@Nullable List<Artist> artists) {
            if (artists == null) {
                return false;
            }

            return artists.stream().anyMatch(this::check);
        }

        private boolean matchesAnyToken(String string) {
            return tokens.stream().anyMatch(string::contains);
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
