package com.app.albumsapplication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.app.albumsapplication.R
import com.app.albumsapplication.databinding.FragmentDetailPhotoBinding

class DetailPhotoFragment : Fragment(R.layout.fragment_detail_photo) {

    private val args by navArgs<DetailPhotoFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailPhotoBinding.bind(view)
        binding.photoImgView.load(args.photoUrl)
    }
}