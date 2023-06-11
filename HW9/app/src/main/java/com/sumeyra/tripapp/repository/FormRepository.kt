package com.sumeyra.tripapp.repository


import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sumeyra.tripapp.model.CityModel
import com.sumeyra.tripapp.utils.constant.Constant
import javax.inject.Inject


class FormRepository @Inject constructor(
    private var auth: FirebaseAuth,
    private var firestore: FirebaseFirestore
) {
    var isOkey = MutableLiveData<Boolean>()
    var isLoading = MutableLiveData<Boolean>()
    val name = auth.currentUser?.uid.toString()
    val cityList: MutableLiveData<List<CityModel>> = MutableLiveData()


    fun addForm(
        title: String,
        city: String,
        notes: String
    ) {
        isLoading.value = true
        val product = hashMapOf(
            Constant.ID to auth.currentUser?.uid,
            Constant.FORM_TITLE to title,
            Constant.FORM_CITY to city,
            Constant.FORM_DESCRIPTION to notes,
        )
        firestore.collection("form").add(product)
            .addOnSuccessListener {
                isOkey.value = true
                isLoading.value = false
            }
            .addOnFailureListener {
                isOkey.value = false
                isLoading.value = false

            }
    }


    fun deleteFromFirebase(city: CityModel) {
        val documentRef = firestore.collection("form").document(city.docId)

        documentRef.delete()
            .addOnSuccessListener {}
            .addOnFailureListener { error -> }
    }


    fun listenDocumentChanges() {
        firestore.collection("form").whereEqualTo("id", name)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) return@addSnapshotListener
                cityList.value = snapshot?.documents?.map { document ->
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