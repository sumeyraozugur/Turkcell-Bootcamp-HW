package com.sumeyra.mybio.ui.communication


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.sumeyra.mybio.R
import com.sumeyra.mybio.databinding.FragmentCommunicationBinding
import com.sumeyra.mybio.delegate.viewBinding
import com.sumeyra.mybio.utils.extension.back
import com.sumeyra.mybio.utils.extension.showSnackbar


class CommunicationFragment : Fragment(R.layout.fragment_communication) {
    private val binding by viewBinding(FragmentCommunicationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbarCommunication.tvToolbarTitle.text = getString(R.string.communication_page)

            toolbarCommunication.ivArrowBack.setOnClickListener {
                Navigation.back(it)
            }

            ivGithub.setOnClickListener {
                view.showSnackbar(getString(R.string.github))
            }

            ivLinkedin.setOnClickListener {
                view.showSnackbar(getString(R.string.linkedin))
            }

            ivMedium.setOnClickListener {
                view.showSnackbar(getString(R.string.medium))
            }

            ivInsegram.setOnClickListener {
                view.showSnackbar(getString(R.string.insegram))
            }

            ivGmail.setOnClickListener {
                view.showSnackbar(getString(R.string.gmail))
            }
        }
    }
}