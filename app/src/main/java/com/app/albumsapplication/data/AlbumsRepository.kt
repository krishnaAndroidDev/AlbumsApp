package com.app.albumsapplication.data

import com.app.albumsapplication.data.api.AlbumApiService
import com.app.albumsapplication.data.api.ApiRequest.apiRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumsRepository @Inject constructor(private val albumApiService: AlbumApiService) {

    suspend fun getAlbumsList() = apiRequest {
        albumApiService.getAlbums()
    }

    suspend fun getPhotosInAlbums(albumId: Int) = apiRequest {
        albumApiService.fetchPhotos(albumId)
    }
}