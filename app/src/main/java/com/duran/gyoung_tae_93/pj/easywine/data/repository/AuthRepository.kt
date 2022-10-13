package com.duran.gyoung_tae_93.pj.easywine.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val _userLiveData = MutableLiveData<FirebaseUser>()

    val userLiveData: LiveData<FirebaseUser>
        get() = _userLiveData

    fun getUser(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (auth.currentUser!!.isEmailVerified) {
                    // 구글 로그인 인증되었을때
                    Log.d("IntroActivity", "Google Login Success")
                } else {
                    // 구글 로그인 인증 실패
                    Log.d("IntroActivity", "Google Login Fail")
                }
            }
        }
    }
}