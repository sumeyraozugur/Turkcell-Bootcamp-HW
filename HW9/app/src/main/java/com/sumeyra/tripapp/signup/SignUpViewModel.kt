package com.sumeyra.tripapp.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sumeyra.tripapp.repository.AuthRepository

class SignUpViewModel:ViewModel() {

    private val firebaseRepo = AuthRepository()

    private var _isSuccess = firebaseRepo.isSuccess
    val isSuccess: LiveData<Boolean> = _isSuccess



    fun signUp(
        eMail: String,
        password: String,
    ) = firebaseRepo.signUp( eMail, password)
}