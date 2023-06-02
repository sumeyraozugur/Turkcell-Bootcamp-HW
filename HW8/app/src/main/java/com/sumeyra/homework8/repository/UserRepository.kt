package com.sumeyra.homework8.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.sumeyra.homework8.model.UserModel
import com.sumeyra.homework8.room.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<UserModel>> = userDao.readAllData()

    suspend fun addUser(user: UserModel) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: UserModel) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: UserModel) {
        userDao.deleteUser(user)
    }

    fun getUsersByGroup(group: String): LiveData<List<UserModel>> {
        return userDao.getUserByGroup(group)
    }

    suspend fun searchDatabase(searchQuery: String): List<UserModel> {
        return userDao.searchDatabase(searchQuery)
        Log.v("Repo", userDao.searchDatabase(searchQuery).toString())


    }

}