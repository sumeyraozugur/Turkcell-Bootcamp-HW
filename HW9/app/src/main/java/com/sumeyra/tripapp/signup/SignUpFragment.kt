package com.sumeyra.tripapp.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sumeyra.tripapp.R
import com.sumeyra.tripapp.databinding.FragmentSignUpBinding
import com.sumeyra.tripapp.delegete.viewBinding
import com.sumeyra.tripapp.utils.extension.isNullorEmpty
import com.sumeyra.tripapp.utils.extension.isValidEmail
import com.sumeyra.tripapp.utils.extension.showErrorSnackBar


class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private val binding by viewBinding(FragmentSignUpBinding::bind)
    private lateinit var viewModel: SignUpViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[SignUpViewModel::class.java]
        initObservers()


        with(binding) {
            btnRegister.setOnClickListener {
                val email = etEmail.isValidEmail("invalid email").toString()   //validation
                val password = etPassword.isNullorEmpty("invalid password").toString()
                viewModel.signUp( email, password)

            }
        }
    }


    private fun initObservers() {
        viewModel.isSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                requireView().showErrorSnackBar("Success",false)

            } else {
                requireView().showErrorSnackBar( "Fail",true)
            }
        })
    }
}
