package com.duran.gyoung_tae_93.pj.easywine.util

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class FBSrg {

    companion object {

        val storage = Firebase.storage
        val storageRef = storage.reference

    }

}