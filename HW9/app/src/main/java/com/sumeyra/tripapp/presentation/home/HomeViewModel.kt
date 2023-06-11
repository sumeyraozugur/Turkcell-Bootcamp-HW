package com.sumeyra.tripapp.presentation.home

import androidx.lifecycle.ViewModel
import com.sumeyra.tripapp.model.CityModel
import com.sumeyra.tripapp.repository.FormRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor( private val firebaseRepo : FormRepository):ViewModel() {

    var cityList = firebaseRepo.cityList

    init {
        firebaseRepo.listenDocumentChanges()
    }

     fun deleteFromFirebase(city: CityModel){
        firebaseRepo.deleteFromFirebase(city)

    }
}