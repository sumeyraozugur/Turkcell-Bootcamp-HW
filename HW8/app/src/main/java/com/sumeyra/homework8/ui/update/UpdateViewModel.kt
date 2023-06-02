package com.sumeyra.homework8.ui.update

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sumeyra.homework8.model.UserModel
import com.sumeyra.homework8.repository.UserRepository
import com.sumeyra.homework8.room.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateViewModel(application: Application): AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val todoDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(todoDao)
    }

    fun updateUser(user: UserModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: UserModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(user)
        }
    }
}