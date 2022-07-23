package com.app.albumsapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.app.albumsapplication.R
import com.app.albumsapplication.data.models.PhotosItem
import com.app.albumsapplication.databinding.ItemPhotosBinding

/**
 * Adapter to show the albums in the recyclerview
 */
class PhotosListAdapter(private val callback: Callback) :
    ListAdapter<PhotosItem, PhotosViewHolder>(PhotosItemDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder =
        PhotosViewHolder(ItemPhotosBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val photosItem = getItem(position)
        holder.bind(photosItem)
        holder.itemView.setOnClickListener {
            callback.onPhotoSelected(photosItem)
        }
    }

    private fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(context)

    interface Callback {
        fun onPhotoSelected(photosItem: PhotosItem)
    }
}

class PhotosItemDiffUtilItemCallback : DiffUtil.ItemCallback<PhotosItem>() {

    override fun areItemsTheSame(oldItem: PhotosItem, newItem: PhotosItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PhotosItem, newItem: PhotosItem) =
        oldItem == newItem
}

class PhotosViewHolder(private val itemPhotosBinding: ItemPhotosBinding) :
    RecyclerView.ViewHolder(itemPhotosBinding.root) {

    fun bind(photosItem: PhotosItem) {
        itemPhotosBinding.photoImgView.load(photosItem.thumbnailUrl) {
            placeholder(R.mipmap.ic_launcher)
        }
    }
}

