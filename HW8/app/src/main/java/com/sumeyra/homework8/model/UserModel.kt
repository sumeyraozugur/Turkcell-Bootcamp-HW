package com.sumeyra.homework8.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class UserModel(

    @PrimaryKey(autoGenerate = true)
    val user_id:Int =0,
    val user_group:String,
    val user_name:String,
    val user_surname:String,
    val user_phone:String,
    val user_address:String

):Parcelable