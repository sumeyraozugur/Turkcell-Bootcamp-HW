package com.sumeyra.tripapp.form

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sumeyra.tripapp.R
import com.sumeyra.tripapp.databinding.FragmentFormBinding
import com.sumeyra.tripapp.delegete.viewBinding
import com.sumeyra.tripapp.utils.extension.showErrorSnackBar


class FormFragment : Fragment(R.layout.fragment_form) {
    private val binding by viewBinding(FragmentFormBinding::bind)
    private val viewModel by viewModels<FormViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProvider(requireActivity())[FormViewModel::class.java]
        initObserver()

        with(binding) {
            btnSave.setOnClickListener {
                val title = etTitle.text.toString().trim { it <= ' ' }
                val city = etCity.text.toString().trim { it <= ' ' }
                val note = etNotes.text.toString().trim { it <= ' ' }
                viewModel.addForm(title,city,note)
            }
        }
    }

    private fun initObserver() {
        viewModel.isOkey.observe(viewLifecycleOwner) {
            if (it) {
                requireView().showErrorSnackBar("Success", false)
                findNavController().popBackStack()
               //findNavController().navigate(R.id.action_formFragment_to_homeFragment)
            } else {
                requireView().showErrorSnackBar("fail", true)
            }
        }
    }
}