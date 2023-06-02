package com.sumeyra.noteapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteModel(
    val id:Int,
    val title:String,
    val detail:String,
    val date:String
):Parcelable
