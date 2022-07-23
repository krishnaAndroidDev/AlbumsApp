package com.app.albumsapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.albumsapplication.data.AlbumsRepository
import com.app.albumsapplication.data.api.Resource
import com.app.albumsapplication.data.models.AlbumsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val albumsRepository: AlbumsRepository) :
    ViewModel() {

    private val _albumsData: MutableLiveData<Resource<List<AlbumsItem?>>> = MutableLiveData()
    val albumsData: LiveData<Resource<List<AlbumsItem?>>> = _albumsData

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    init {
        getAlbumsList()
    }

    private fun getAlbumsList() {
        viewModelScope.launch {
            _loading.postValue(true)
            try {
                val response = albumsRepository.getAlbumsList()
                _albumsData.postValue(Resource.Success(response))
            } catch (e: Exception) {
                _albumsData.postValue(Resource.Error(e.message.toString()))
            }
            _loading.postValue(false)
        }
    }
}