package com.sgssb.instacard.ui.transactions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sgssb.instacard.models.ExchangeModel

class TransactionViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val uid = FirebaseAuth.getInstance().uid

    fun getTransactions() : MutableLiveData<List<ExchangeModel>> {
        val exchangeList = MutableLiveData<List<ExchangeModel>>()

        db.collection("users/${uid}/transactions").addSnapshotListener { exchangeDocuments, error ->
            if (error == null){
                exchangeList.value = exchangeDocuments?.toObjects(ExchangeModel::class.java)
            }
        }

        return exchangeList
    }
}