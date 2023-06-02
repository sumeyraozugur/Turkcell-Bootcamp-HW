package com.sumeyra.centralbank.model

data class CentralBankModel(
    val currencyName: String,
    val forexBuying: String,
    val forexSelling: String,
    val banknoteBuying: String,
    val banknoteSelling: String
)