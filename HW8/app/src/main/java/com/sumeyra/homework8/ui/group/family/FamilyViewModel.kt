package com.sumeyra.homework8.ui.group.family

import android.app.Application
import androidx.lifecycle.*
import com.sumeyra.homework8.model.UserModel
import com.sumeyra.homework8.repository.UserRepository
import com.sumeyra.homework8.room.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FamilyViewModel(application: Application): AndroidViewModel(application){

    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)

    }

    fun getUsersByGroup(group: String): LiveData<List<UserModel>> {
        return repository.getUsersByGroup(group)
    }
}