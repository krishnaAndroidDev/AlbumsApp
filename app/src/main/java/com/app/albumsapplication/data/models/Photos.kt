package com.app.albumsapplication.data.models

import com.google.gson.annotations.SerializedName

data class Photos(

	@field:SerializedName("Photos")
	val photos: List<PhotosItem?>? = null
)

data class PhotosItem(

	@field:SerializedName("albumId")
	val albumId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("thumbnailUrl")
	val thumbnailUrl: String? = null
)
