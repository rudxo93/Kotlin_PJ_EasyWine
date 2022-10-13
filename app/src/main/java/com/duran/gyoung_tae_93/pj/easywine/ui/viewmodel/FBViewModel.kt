package com.duran.gyoung_tae_93.pj.easywine.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.duran.gyoung_tae_93.pj.easywine.data.repository.FBRepository

class FBViewModel : ViewModel() {

    private var fbRepository: FBRepository = FBRepository()

    fun insertNk(nickname: String) {
        fbRepository.insertNk(nickname)
    }
}