package com.sumeyra.tripapp.presentation.forgot

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sumeyra.tripapp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val firebaseRepo :AuthRepository) :ViewModel(){

    private val _isSuccess = firebaseRepo.isSuccess
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun changePassword(email: String) = firebaseRepo.changePassword(email)
}