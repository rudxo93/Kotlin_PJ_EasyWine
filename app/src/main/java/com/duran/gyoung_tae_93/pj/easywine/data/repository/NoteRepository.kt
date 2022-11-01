package com.duran.gyoung_tae_93.pj.easywine.data.repository

import com.duran.gyoung_tae_93.pj.easywine.data.dao.NoteDao
import com.duran.gyoung_tae_93.pj.easywine.data.model.NoteInfoModel

class NoteRepository {

    private val noteDao = NoteDao()

    fun insertNoteInfo(noteInfo: NoteInfoModel, key: String) = noteDao.insertNoteInfo(noteInfo, key)

}