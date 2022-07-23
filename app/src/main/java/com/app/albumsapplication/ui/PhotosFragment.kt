package com.app.albumsapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.albumsapplication.R
import com.app.albumsapplication.adapters.PhotosListAdapter
import com.app.albumsapplication.data.api.Resource
import com.app.albumsapplication.data.models.PhotosItem
import com.app.albumsapplication.databinding.FragmentPhotosBinding

class PhotosFragment : Fragment(R.layout.fragment_photos) {

    private val photosViewModel: PhotosViewModel by activityViewModels()
    private val args by navArgs<PhotosFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPhotosBinding.bind(view)

        val listAdapter = PhotosListAdapter(
            object : PhotosListAdapter.Callback {
                override fun onPhotoSelected(photosItem: PhotosItem) {
                    findNavController().navigate(
                        PhotosFragmentDirections.actionPhotosFragmentToDetailPhotoFragment(
                            photosItem.url
                        )
                    )
                }
            }
        )
        binding.photosRecyclerView.adapter = listAdapter

        photosViewModel.getPhotosOfAlbum(args.albumId)
        photosViewModel.photosData.observe(viewLifecycleOwner, Observer {
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

        photosViewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.loadingProgressBar.isVisible = it
        })
    }
}