package com.sumeyra.tripapp.forgot

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sumeyra.tripapp.repository.AuthRepository

class ForgotPasswordViewModel :ViewModel(){
    private val firebaseRepo = AuthRepository()

    private val _isSuccess = firebaseRepo.isSuccess
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun changePassword(email: String) = firebaseRepo.changePassword(email)

}