package com.sumeyra.tripapp.forgot

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sumeyra.tripapp.R
import com.sumeyra.tripapp.databinding.FragmentForgotPasswordBinding
import com.sumeyra.tripapp.delegete.viewBinding
import com.sumeyra.tripapp.utils.extension.isValidEmail
import com.sumeyra.tripapp.utils.extension.showErrorSnackBar


class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {
    private val binding by viewBinding(FragmentForgotPasswordBinding::bind)
    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ForgotPasswordViewModel::class.java]
        observeChangePassword()

        with(binding){
            btnReset.setOnClickListener {
                val emailResult = etEmail.isValidEmail("invalid email")
                if (emailResult) {
                    val email = etEmail.text.toString()
                    viewModel.changePassword(email)
                }
            }
        }
    }


    private fun observeChangePassword() {
        viewModel.isSuccess.observe(viewLifecycleOwner) {
            if (it) {
                requireView().showErrorSnackBar("Email sent", false)
                findNavController().navigate(R.id.action_forgotPasswordFragment_to_signInFragment)
            } else {
                requireView().showErrorSnackBar("fail", true)
            }
        }
    }


}