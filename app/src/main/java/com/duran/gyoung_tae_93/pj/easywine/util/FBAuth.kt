package com.duran.gyoung_tae_93.pj.easywine.util

import com.google.firebase.auth.FirebaseAuth

class FBAuth {
    companion object {

        private var auth: FirebaseAuth = FirebaseAuth.getInstance()

        fun getCurrentUser() : String {
            return auth.currentUser.toString()
        }

        fun getUid() : String {
            return auth.currentUser?.uid.toString()
        }

        fun getEmail() : String {
            return auth.currentUser?.email.toString()
        }
    }
}