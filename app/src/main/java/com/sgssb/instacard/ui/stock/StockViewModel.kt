package com.sgssb.instacard.ui.stock

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.sgssb.instacard.models.StockModel

class StockViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    fun getStock() : MutableLiveData<List<StockModel>> {
        val stockList = MutableLiveData<List<StockModel>>()

        db.collection("stocks").addSnapshotListener { exchangeDocuments, error ->
            if (error == null){
                stockList.value = exchangeDocuments?.toObjects(StockModel::class.java)
            }
        }

        return stockList
    }
}