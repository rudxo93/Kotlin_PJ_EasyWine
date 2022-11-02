package com.duran.gyoung_tae_93.pj.easywine.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duran.gyoung_tae_93.pj.easywine.data.dao.NoteDao
import com.duran.gyoung_tae_93.pj.easywine.data.model.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.util.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class NoteRepository {

    private val noteDao = NoteDao()

    fun insertNoteInfo(noteInfo: NoteInfoModel, key: String) = noteDao.insertNoteInfo(noteInfo, key)

    fun getNoteData(): LiveData<MutableList<NoteInfoModel>> = noteDao.getNoteData()

}