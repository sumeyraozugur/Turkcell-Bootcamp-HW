package com.sumeyra.tripapp.presentation.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sumeyra.tripapp.R
import com.sumeyra.tripapp.databinding.FragmentSignUpBinding
import com.sumeyra.tripapp.delegete.viewBinding
import com.sumeyra.tripapp.utils.extension.isNullorEmpty
import com.sumeyra.tripapp.utils.extension.isValidEmail
import com.sumeyra.tripapp.utils.extension.showErrorSnackBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private val binding by viewBinding(FragmentSignUpBinding::bind)
    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initObservers()

        with(binding) {
            btnRegister.setOnClickListener {
                val email = etEmail.isValidEmail("invalid email")  //validation
                val password = etPassword.isNullorEmpty("invalid password")
                if (email && password) {
                    val email = etEmail.text.toString()
                    val password = etPassword.text.toString()

                    viewModel.signUp(email, password)

                }
            }
        }
    }


    private fun initObservers() {
        viewModel.isSuccess.observe(viewLifecycleOwner){
            if (it) {
                requireView().showErrorSnackBar("Success",false)
                findNavController().navigate(R.id.action_signUpFragment_to_main_graph)

            } else {
                requireView().showErrorSnackBar( "Fail",true)
            }
        }
    }
}
