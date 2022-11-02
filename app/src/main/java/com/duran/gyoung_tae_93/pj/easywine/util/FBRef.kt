package com.duran.gyoung_tae_93.pj.easywine.util

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FBRef {

    companion object {

        private val database = Firebase.database

        val noteNote = database.getReference("wine_note")

    }

}