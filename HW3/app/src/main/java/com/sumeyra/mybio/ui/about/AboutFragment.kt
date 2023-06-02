package com.sumeyra.mybio.ui.about


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.sumeyra.mybio.R
import com.sumeyra.mybio.databinding.FragmentAboutBinding
import com.sumeyra.mybio.delegate.viewBinding
import com.sumeyra.mybio.utils.extension.back


class AboutFragment : Fragment(R.layout.fragment_about) {

    private val binding by viewBinding(FragmentAboutBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            aboutToolbar.tvToolbarTitle.text = getString(R.string.about_page)

            aboutToolbar.ivArrowBack.setOnClickListener {
                Navigation.back(it)
            }
        }
    }

}