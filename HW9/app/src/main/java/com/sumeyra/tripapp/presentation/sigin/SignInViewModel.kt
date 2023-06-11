package com.sumeyra.tripapp.presentation.sigin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sumeyra.tripapp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val firebaseRepo : AuthRepository):ViewModel() {

    val isSignIn: LiveData<Boolean> = firebaseRepo.isSignIn

    fun signIn(email:String,password:String) = firebaseRepo.signIn(email, password)

}








