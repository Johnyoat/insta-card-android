package com.sgssb.instacard.ui.transactionService

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.sgssb.instacard.models.TransferServiceModel

class TransactionServiceViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

     fun getTransactionsServiceBy(name:String):MutableLiveData<List<TransferServiceModel>> {
        val transferServiceList = MutableLiveData<List<TransferServiceModel>>()
        db.collection(name).addSnapshotListener { documentSnapShots, error ->
            if (error == null){
                transferServiceList.value = documentSnapShots!!.toObjects(TransferServiceModel::class.java)
            }
        }

        return transferServiceList
    }
}