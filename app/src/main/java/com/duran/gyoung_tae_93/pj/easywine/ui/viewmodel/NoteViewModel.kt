package com.duran.gyoung_tae_93.pj.easywine.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duran.gyoung_tae_93.pj.easywine.data.model.note.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel: ViewModel() {

    private val noteRepository = NoteRepository()

    fun getNoteData(currentUid: String): LiveData<MutableList<NoteInfoModel>> {
        val mutableData = MutableLiveData<MutableList<NoteInfoModel>>()
        noteRepository.getNoteData(currentUid).observeForever {
            mutableData.value = it
        }
        return mutableData
    }

    fun insertNoteInfo(noteInfo: NoteInfoModel) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insertNoteInfo(noteInfo)
    }

}