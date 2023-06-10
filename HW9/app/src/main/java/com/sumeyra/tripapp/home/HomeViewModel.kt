package com.sumeyra.tripapp.home

import androidx.lifecycle.ViewModel
import com.sumeyra.tripapp.model.CityModel
import com.sumeyra.tripapp.repository.FormRepository

class HomeViewModel:ViewModel() {
    private val firebaseRepo = FormRepository()
    var cityList = firebaseRepo.cityList

    init {
        firebaseRepo.listenDocumentChanges()
    }

     fun deleteFromFirebase(city: CityModel){
        firebaseRepo.deleteFromFirebase(city)

    }
}