package com.sumeyra.mybio.ui.home


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.sumeyra.mybio.R
import com.sumeyra.mybio.databinding.FragmentHomeBinding
import com.sumeyra.mybio.delegate.viewBinding
import com.sumeyra.mybio.utils.extension.sent


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding ( FragmentHomeBinding::bind )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            btnAbout.setOnClickListener {
                Navigation.sent(it,R.id.action_homeFragment_to_aboutFragment)
            }

            btnBlog.setOnClickListener {
                Navigation.sent(it,R.id.action_homeFragment_to_blogFragment)
            }

            btnGallery.setOnClickListener {
                Navigation.sent(it,R.id.action_homeFragment_to_galleryFragment)
            }

            btnConnection.setOnClickListener {
                Navigation.sent(it,R.id.action_homeFragment_to_communicationFragment)
            }
        }

    }
}