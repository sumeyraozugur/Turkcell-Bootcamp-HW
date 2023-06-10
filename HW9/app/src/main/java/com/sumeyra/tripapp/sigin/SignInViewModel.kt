package com.sumeyra.tripapp.sigin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumeyra.tripapp.repository.AuthRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SignInViewModel:ViewModel() {

    private val firebaseRepo = AuthRepository()


    val isSignIn: LiveData<Boolean> = firebaseRepo.isSignIn

    fun signIn(email:String,password:String) = firebaseRepo.signIn(email, password)




}








