package com.sgssb.instacard.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.sgssb.instacard.models.UserModel

class HomeViewModel : ViewModel() {
   private val db = FirebaseFirestore.getInstance()
    private val uid = FirebaseAuth.getInstance().currentUser?.uid
  fun getUserDetail():MutableLiveData<UserModel>{
      val user = MutableLiveData<UserModel>()
        db.document("users/$uid").addSnapshotListener{userSnapshot,error ->
            if (error == null){
                user.value = userSnapshot?.toObject()
            }
        }
      return user
  }
}