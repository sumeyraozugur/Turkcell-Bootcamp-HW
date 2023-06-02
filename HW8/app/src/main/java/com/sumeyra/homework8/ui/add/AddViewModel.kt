package com.sumeyra.homework8.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sumeyra.homework8.model.UserModel
import com.sumeyra.homework8.repository.UserRepository
import com.sumeyra.homework8.room.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<UserModel>>
    private val repository: UserRepository


    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)

        }
    }
}