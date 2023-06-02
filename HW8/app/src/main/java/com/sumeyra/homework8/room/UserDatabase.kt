package com.sumeyra.homework8.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sumeyra.homework8.model.UserModel

@Database(entities = [UserModel::class], version = 3, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao():UserDao

    companion object{
        private var INSTANCE: UserDatabase?= null

        fun getDatabase(context: Context):UserDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE =instance
                return instance
            }
        }
    }
}