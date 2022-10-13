package com.duran.gyoung_tae_93.pj.easywine.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.duran.gyoung_tae_93.pj.easywine.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class AuthViewModel : ViewModel() {
    private var authRepository: AuthRepository = AuthRepository()
    private val _userLiveData = authRepository.userLiveData

    val userLiveData: LiveData<FirebaseUser>
        get() = _userLiveData

    fun getUser(idToken: String) {
        authRepository.getUser(idToken)
    }
}