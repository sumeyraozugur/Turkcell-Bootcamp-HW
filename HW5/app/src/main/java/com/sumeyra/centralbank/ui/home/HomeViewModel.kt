package com.sumeyra.centralbank.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumeyra.centralbank.repository.CentralBankRepository

class HomeViewModel : ViewModel() {
    private val repository = CentralBankRepository()
    val bankList = repository.bankList
    val date = repository.date

    fun getBankRates() { //viewmodel scope
        repository.getBankRates()
    }
}