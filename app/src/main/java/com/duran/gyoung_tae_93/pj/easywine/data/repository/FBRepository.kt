package com.duran.gyoung_tae_93.pj.easywine.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duran.gyoung_tae_93.pj.easywine.data.model.UserModel
import com.duran.gyoung_tae_93.pj.easywine.util.FBAuth
import com.duran.gyoung_tae_93.pj.easywine.util.FBDocRef
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class FBRepository {

    fun insertNk(nickname : String) {
        val userModel = UserModel()

        userModel.email = FBAuth.getEmail()
        userModel.uid = FBAuth.getUid()
        userModel.nickname = nickname
        userModel.timestamp = System.currentTimeMillis()

        FBDocRef.fsDB.collection("user").document(FBAuth.getUid()).set(userModel)
    }
}