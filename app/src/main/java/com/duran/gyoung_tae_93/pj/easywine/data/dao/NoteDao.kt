package com.duran.gyoung_tae_93.pj.easywine.data.dao

import com.duran.gyoung_tae_93.pj.easywine.data.model.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.util.FBRef
import com.google.android.gms.tasks.Task

class NoteDao {

    fun insertNoteInfo(noteInfo: NoteInfoModel, key: String): Task<Void> {
        return FBRef.noteNote.child(key).setValue(noteInfo)
    }

}