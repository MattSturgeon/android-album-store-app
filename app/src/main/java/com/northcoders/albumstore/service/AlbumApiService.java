package com.northcoders.albumstore.service;

import com.northcoders.albumstore.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AlbumApiService {

    @GET("albums")
    Call<List<Album>> listAlbums();

    @POST("albums")
    Call<Album> addAlbum(@Body Album album);
}
