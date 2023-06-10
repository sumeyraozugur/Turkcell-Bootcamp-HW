package com.sumeyra.tripapp.sigin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.sumeyra.tripapp.R
import com.sumeyra.tripapp.databinding.FragmentSignInBinding
import com.sumeyra.tripapp.delegete.viewBinding
import com.sumeyra.tripapp.utils.extension.isNullorEmpty
import com.sumeyra.tripapp.utils.extension.isValidEmail
import com.sumeyra.tripapp.utils.extension.sent
import com.sumeyra.tripapp.utils.extension.showErrorSnackBar


class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private val binding by viewBinding(FragmentSignInBinding::bind)
    private lateinit var viewModel: SignInViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SignInViewModel::class.java]
        initObservers()

        with(binding) {

            tvPassword.setOnClickListener {
                Navigation.sent(it, R.id.action_signInFragment_to_forgotPasswordFragment)
            }

            tvCreateAccount.setOnClickListener {
                Navigation.sent(it, R.id.action_signInFragment_to_signUpFragment)
            }

            btnLogin.setOnClickListener {

                val emailResult =
                    etEmail.isValidEmail("invalid email")   //validation
                val passwordResult = etPassword.isNullorEmpty("invalid password")

                if (emailResult && passwordResult) {
                    val email = etEmail.text.toString()
                    val password = etPassword.text.toString()

                    viewModel.signIn(email, password)

                }
            }

        }
    }

    private fun initObservers() {
        viewModel.isSignIn.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_signInFragment_to_main_graph)
            } else {
                requireView().showErrorSnackBar("fail", true)
            }
        }
    }
}

