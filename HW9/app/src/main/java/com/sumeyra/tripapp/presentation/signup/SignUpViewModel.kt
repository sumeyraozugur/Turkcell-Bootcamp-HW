package com.sumeyra.tripapp.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sumeyra.tripapp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val firebaseRepo: AuthRepository):ViewModel() {

    private var _isSuccess = firebaseRepo.isSuccess
    val isSuccess: LiveData<Boolean> = _isSuccess


    fun signUp(
        eMail: String,
        password: String,
    ) = firebaseRepo.signUp( eMail, password)
}