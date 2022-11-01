package com.duran.gyoung_tae_93.pj.easywine.util

import android.annotation.SuppressLint
import com.google.firebase.firestore.FirebaseFirestore

class FBDocRef {

    companion object {
        @SuppressLint("StaticFieldLeak")
        val fbDB = FirebaseFirestore.getInstance()
    }

}