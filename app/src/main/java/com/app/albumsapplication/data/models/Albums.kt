package com.app.albumsapplication.data.models

import com.google.gson.annotations.SerializedName

data class Albums(

	@field:SerializedName("Albums")
	val albums: List<AlbumsItem?>? = null
)

data class AlbumsItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)
