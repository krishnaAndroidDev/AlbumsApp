package com.app.albumsapplication.data.api

import com.app.albumsapplication.data.models.Albums
import com.app.albumsapplication.data.models.AlbumsItem
import com.app.albumsapplication.data.models.Photos
import com.app.albumsapplication.data.models.PhotosItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumApiService {

    @GET("albums")
    suspend fun getAlbums(): Response<List<AlbumsItem?>>

    @GET("photos")
    suspend fun fetchPhotos(@Query("albumId") albumId: Int): Response<List<PhotosItem?>>
}