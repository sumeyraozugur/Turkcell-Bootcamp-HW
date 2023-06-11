package com.sumeyra.tripapp.presentation.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sumeyra.tripapp.repository.FormRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(private val firebaseRepo : FormRepository):ViewModel() {
    val isOkey: LiveData<Boolean> = firebaseRepo.isOkey

    fun addForm(title: String, city: String, notes: String) {
        firebaseRepo.addForm(title, city, notes)
    }
}