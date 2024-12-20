package com.northcoders.albumstore.service;

import com.northcoders.albumstore.model.Album;
import com.northcoders.albumstore.model.AlbumRequestDTO;
import com.northcoders.albumstore.model.Genre;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AlbumApiService {

    @GET("albums")
    Call<List<Album>> listAlbums();

    @POST("albums")
    Call<Album> addAlbum(@Body AlbumRequestDTO album);

    @PUT("albums/{id}")
    Call<Album> updateAlbum(@Path("id") long id, @Body AlbumRequestDTO album);

    @DELETE("albums/{id}")
    Call<Void> removeAlbum(@Path("id") long id);

    @GET("genres")
    Call<Map<String, Genre>> listGenres();
}
