package com.sumeyra.tripapp.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sumeyra.tripapp.repository.FormRepository
import kotlinx.coroutines.channels.Channel

class FormViewModel:ViewModel() {
    private val firebaseRepo = FormRepository()

    val isOkey: LiveData<Boolean> = firebaseRepo.isOkey


    fun addForm(title: String, city: String, notes: String) {
        firebaseRepo.addForm(title, city, notes)
    }
}