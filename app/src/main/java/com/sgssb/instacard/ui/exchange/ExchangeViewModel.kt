package com.sgssb.instacard.ui.exchange

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.sgssb.instacard.models.ExchangeModel

class ExchangeViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    fun getExchange() : MutableLiveData<List<ExchangeModel>>{
        val exchangeList = MutableLiveData<List<ExchangeModel>>()

        db.collection("exchange").addSnapshotListener { exchangeDocuments, error ->
            if (error == null){
                exchangeList.value = exchangeDocuments?.toObjects(ExchangeModel::class.java)
            }
        }

        return exchangeList
    }
}