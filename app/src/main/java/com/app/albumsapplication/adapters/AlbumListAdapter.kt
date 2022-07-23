package com.app.albumsapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.albumsapplication.data.models.AlbumsItem
import com.app.albumsapplication.databinding.ItemAlbumsBinding

/**
 * Adapter to show the albums in the recyclerview
 */
class AlbumListAdapter(private val callback: Callback) : ListAdapter<AlbumsItem, AlbumViewHolder>(DiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
        AlbumViewHolder(ItemAlbumsBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int){
        val albumItem = getItem(position)
        holder.bind(albumItem)
        holder.itemView.setOnClickListener {
            callback.onAlbumSelected(albumItem)
        }
    }


    private fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(context)

    interface Callback {
        fun onAlbumSelected(albumsItem: AlbumsItem)
    }
}

class DiffUtilItemCallback : DiffUtil.ItemCallback<AlbumsItem>() {

    override fun areItemsTheSame(oldItem: AlbumsItem, newItem: AlbumsItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: AlbumsItem, newItem: AlbumsItem) =
        oldItem == newItem
}

class AlbumViewHolder(private val itemAlbumsBinding: ItemAlbumsBinding) :
    RecyclerView.ViewHolder(itemAlbumsBinding.root) {

    fun bind(albumArt: AlbumsItem) {
        itemAlbumsBinding.tvAlbumTitle.text = albumArt.title
    }
}

