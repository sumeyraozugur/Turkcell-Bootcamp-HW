package com.sumeyra.homework8.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sumeyra.homework8.model.UserModel


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(todo: UserModel)

    @Query("SELECT * FROM user_table ORDER BY user_id ASC")
    fun readAllData(): LiveData<List<UserModel>>

    @Update
    suspend fun updateUser(user: UserModel)

    @Delete
    suspend fun deleteUser(user: UserModel)

    @Query("SELECT * FROM user_table WHERE user_group = :group")
    fun getUserByGroup(group: String): LiveData<List<UserModel>>

    @Query("SELECT * FROM user_table WHERE user_name  like '%' || :searchQuery || '%'  ") //WHERE firstName  like '%' || :searchQuery || lastName like '%'
    suspend fun searchDatabase(searchQuery: String): List<UserModel>


}