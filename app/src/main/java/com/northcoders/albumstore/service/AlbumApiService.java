package com.northcoders.albumstore.service;

import com.northcoders.albumstore.model.AlbumDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumApiService {

    @GET("albums")
    Call<List<AlbumDTO>> listAlbums();
}
