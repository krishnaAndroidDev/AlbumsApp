package com.app.albumsapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.albumsapplication.R
import com.app.albumsapplication.adapters.AlbumListAdapter
import com.app.albumsapplication.data.api.Resource
import com.app.albumsapplication.data.models.AlbumsItem
import com.app.albumsapplication.databinding.FragmentAlbumsBinding

class AlbumsFragment : Fragment(R.layout.fragment_albums) {

    private val albumsViewModel: AlbumsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAlbumsBinding.bind(view)

        val listAdapter = AlbumListAdapter(object : AlbumListAdapter.Callback {
            override fun onAlbumSelected(albumsItem: AlbumsItem) {
                findNavController().navigate(
                    AlbumsFragmentDirections.actionAlbumsFragmentToPhotosFragment(
                        albumsItem.id ?: -1
                    )
                )
            }
        })

        binding.albumsRecyclerView.apply {
            adapter = listAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }

        albumsViewModel.albumsData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    listAdapter.submitList(it.data)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        albumsViewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.loadingProgressBar.isVisible = it
        })
    }
}