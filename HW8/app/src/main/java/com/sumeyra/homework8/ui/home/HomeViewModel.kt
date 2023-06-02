package com.sumeyra.homework8.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sumeyra.homework8.model.UserModel
import com.sumeyra.homework8.repository.UserRepository
import com.sumeyra.homework8.room.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel(application: Application): AndroidViewModel(application){
    val readAllData: LiveData<List<UserModel>>
    private val repository: UserRepository
    var tempList = MutableLiveData<List<UserModel>>()



    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun searchDatabase(searchQuery:String){
        viewModelScope.launch(Dispatchers.IO){

            tempList.postValue(repository.searchDatabase(searchQuery))
            Log.v("ViewModel",repository.searchDatabase(searchQuery).toString())

        }

    }

}