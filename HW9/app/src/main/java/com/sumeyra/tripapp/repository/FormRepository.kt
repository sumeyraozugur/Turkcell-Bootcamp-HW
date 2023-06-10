package com.sumeyra.tripapp.repository


import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sumeyra.tripapp.model.CityModel
import com.sumeyra.tripapp.utils.constant.Constant


class FormRepository {
    var isOkey = MutableLiveData<Boolean>()
    var isLoading = MutableLiveData<Boolean>()
    private var auth = Firebase.auth
    val name = auth.currentUser?.uid.toString()
    var firestore = Firebase.firestore
    val cityList: MutableLiveData<List<CityModel>> = MutableLiveData()



    fun addForm(
        title: String,
        city: String,
        notes: String
    ) {
        isLoading.value = true
        val product = hashMapOf(
            Constant.ID to auth.currentUser?.uid,
            Constant.PRODUCT_TITLE to title,
            Constant.FORM_CITY to city,
            Constant.PRODUCT_DESCRIPTION to notes,
        )
        firestore.collection("form").add(product)
            .addOnSuccessListener {
                isOkey.value = true
                isLoading.value = false
                //  Log.d("Product", Constant.SUCCESS)

            }
            .addOnFailureListener {
                isOkey.value = false
                isLoading.value = false
                //  Log.w("Product", exception)

            }

    }



    fun deleteFromFirebase(city:CityModel){
        val documentRef = firestore.collection("form").document(city.docId)

        documentRef.delete()
            .addOnSuccessListener {


            }
            .addOnFailureListener { error ->

            }
    }

    fun listenDocumentChanges() {
        firestore.collection("form").whereEqualTo("id", name).addSnapshotListener { snapshot, exception ->
           if( exception != null ) return@addSnapshotListener
            cityList.value= snapshot?.documents?.map { document ->
                CityModel(
                    document.id,
                    document.getString("id") ?: "",
                    document.getString("title") ?: "",
                    document.getString("city") ?: "",
                    document.getString("description") ?: ""
                )

            }

        }
    }
}