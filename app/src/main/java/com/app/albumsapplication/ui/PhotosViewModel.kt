package com.app.albumsapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.albumsapplication.data.AlbumsRepository
import com.app.albumsapplication.data.api.Resource
import com.app.albumsapplication.data.models.PhotosItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val albumsRepository: AlbumsRepository) :
    ViewModel() {

    private val _photosData: MutableLiveData<Resource<List<PhotosItem?>>> = MutableLiveData()
    val photosData: LiveData<Resource<List<PhotosItem?>>> = _photosData

    private val _selectedPhotoItem: MutableLiveData<PhotosItem> = MutableLiveData()
    val selectedPhotoItem: LiveData<PhotosItem> = _selectedPhotoItem

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    fun getPhotosOfAlbum(albumId: Int) {
        viewModelScope.launch {
            _loading.postValue(true)
            try {
                val response = albumsRepository.getPhotosInAlbums(albumId)
                _photosData.postValue(Resource.Success(response))
            } catch (e: Exception) {
                _photosData.postValue(Resource.Error(e.message.toString()))
            }
            _loading.postValue(false)

        }
    }

    fun onPhotoSelected(photosItem: PhotosItem) {
        _selectedPhotoItem.postValue(photosItem)
    }
}