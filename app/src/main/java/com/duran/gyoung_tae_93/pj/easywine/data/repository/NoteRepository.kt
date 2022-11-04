package com.duran.gyoung_tae_93.pj.easywine.data.repository

import androidx.lifecycle.LiveData
import com.duran.gyoung_tae_93.pj.easywine.data.dao.NoteDao
import com.duran.gyoung_tae_93.pj.easywine.data.model.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.NoteRVAdapter

class NoteRepository {

    private val noteDao = NoteDao()

    fun insertNoteInfo(noteInfo: NoteInfoModel) = noteDao.insertNoteInfo(noteInfo)

    fun getNoteData(): LiveData<MutableList<NoteInfoModel>> = noteDao.getNoteData()

}