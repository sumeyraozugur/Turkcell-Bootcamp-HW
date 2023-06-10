package com.sumeyra.tripapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sumeyra.tripapp.utils.constant.Constant.E_MAIL
import com.sumeyra.tripapp.utils.constant.Constant.ID
import com.sumeyra.tripapp.utils.constant.Constant.SIGN_IN
import com.sumeyra.tripapp.utils.constant.Constant.SIGN_UP
import com.sumeyra.tripapp.utils.constant.Constant.SUCCESS
import com.sumeyra.tripapp.utils.constant.Constant.USERS_PATH

class AuthRepository {
    private var auth = Firebase.auth
    private var firebaseFirestore = Firebase.firestore
    var isLoading = MutableLiveData<Boolean>()
    var isSignIn = MutableLiveData<Boolean>()
    var isSuccess = MutableLiveData<Boolean>()


    //Register
    fun signUp(
        eMail: String,
        password: String,
    ) {
        isLoading.value = true
        auth.createUserWithEmailAndPassword(eMail, password).addOnCompleteListener { task ->
            isLoading.value = false
            if (task.isSuccessful) {
                val currentUser = auth.currentUser
                currentUser?.let { firebaseUser ->
                    val user = hashMapOf(
                        ID to firebaseUser.uid,
                        E_MAIL to eMail,
                    )
                    firebaseFirestore.collection(USERS_PATH).document(firebaseUser.uid)
                        .set(user)
                        .addOnSuccessListener {
                            isSuccess.value = true
                            Log.d(SIGN_UP, SUCCESS)
                        }
                        .addOnFailureListener { exception ->
                            isSuccess.value = false
                            Log.w(SIGN_UP, exception)
                        }
                }
            } else {
                isSuccess.value = false
                Log.w(SIGN_UP, task.exception)
            }
        }
    }


    //SignIn
    fun signIn(email: String, password: String) {
        isLoading.value = true
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {authResult->
            isLoading.value = false
            authResult?.let {
                isSignIn.value = true
                Log.w(SIGN_IN, "okey")
            }
        }.addOnFailureListener {
            isSignIn.value = false
            isLoading.value = false
        }
    }

    //Change Password
    fun changePassword(email: String) {
        isLoading.value = true
        auth.sendPasswordResetEmail(email).addOnSuccessListener {
            isSuccess.value = true
            isLoading.value = false
        }.addOnFailureListener {
            isSuccess.value = false
            isLoading.value = false
        }
    }


}