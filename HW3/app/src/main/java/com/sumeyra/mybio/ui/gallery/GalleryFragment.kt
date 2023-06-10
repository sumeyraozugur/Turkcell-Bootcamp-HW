package com.sumeyra.mybio.ui.gallery


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.sumeyra.mybio.R
import com.sumeyra.mybio.data.GalleryList.galleryList
import com.sumeyra.mybio.databinding.FragmentGalleryBinding
import com.sumeyra.mybio.delegate.viewBinding
import com.sumeyra.mybio.utils.extension.back


class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    private val binding by viewBinding (FragmentGalleryBinding::bind)
    private val adapter by lazy { GalleryAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbarGallery.tvToolbarTitle.text = getString(R.string.gallery_page)
            toolbarGallery.ivArrowBack.setOnClickListener {
                Navigation.back(it)

            }


            //Adapter
            recyclerview.adapter = adapter
            adapter.updateList(galleryList)
        }
    }

}