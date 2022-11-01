package com.duran.gyoung_tae_93.pj.easywine.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duran.gyoung_tae_93.pj.easywine.data.model.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel: ViewModel() {

    private val noteRepository = NoteRepository()

    fun insertNoteInfo(noteInfo: NoteInfoModel, key: String) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insertNoteInfo(noteInfo, key)
    }

}