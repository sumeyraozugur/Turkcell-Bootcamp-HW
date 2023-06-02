package com.sumeyra.centralbank.repository

import androidx.lifecycle.MutableLiveData
import com.sumeyra.centralbank.model.CentralBankModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class CentralBankRepository {

    val bankList = MutableLiveData<List<CentralBankModel>>()
    var date = MutableLiveData<String>()

    fun getBankRates() {
        CoroutineScope(Dispatchers.IO).launch {
            val arr = mutableListOf<CentralBankModel>()
            val url = "https://www.tcmb.gov.tr/kurlar/today.xml"
            val doc: Document = Jsoup.connect(url).timeout(15000).get()
            val elements: Elements = doc.getElementsByTag("Currency")
            date.postValue(doc.getElementsByTag("Tarih_Date").attr("Tarih"))
            // Log.v("datee",date)
            for (item in elements) {
                // Log.v("itemm",item.getElementsByTag("CurrencyName").text())
                val currencyName = item.getElementsByTag("CurrencyName").text().toString()
                val forexBuying = item.getElementsByTag("ForexBuying").text().toString()
                val forexSelling = item.getElementsByTag("ForexSelling").text().toString()
                val banknoteBuying = item.getElementsByTag("BanknoteBuying").text().toString()
                val banknoteSelling = item.getElementsByTag("BanknoteSelling").text().toString()

                if (currencyName != "" && forexBuying != "" && forexSelling != "" && banknoteBuying != "" && banknoteSelling != "") {
                    val centralBankModel = CentralBankModel(
                        currencyName,
                        forexBuying,
                        forexSelling,
                        banknoteBuying,
                        banknoteSelling
                    )
                    arr.add(centralBankModel)
                }
            }
            bankList.postValue(arr)
        }
    }
}