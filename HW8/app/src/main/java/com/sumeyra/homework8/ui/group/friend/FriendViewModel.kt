package com.sumeyra.homework8.ui.group.friend


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sumeyra.homework8.model.UserModel
import com.sumeyra.homework8.repository.UserRepository
import com.sumeyra.homework8.room.UserDatabase

class FriendViewModel(application: Application): AndroidViewModel(application){

    private val repository: UserRepository


    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun getUsersByGroup(group: String): LiveData<List<UserModel>> {
        return repository.getUsersByGroup(group)
    }
}

