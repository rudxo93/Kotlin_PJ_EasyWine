package com.duran.gyoung_tae_93.pj.easywine.data.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duran.gyoung_tae_93.pj.easywine.data.model.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.util.FBDocRef
import com.google.android.gms.tasks.Task

class NoteDao {

    fun insertNoteInfo(noteInfo: NoteInfoModel): Task<Void> {
        return FBDocRef.fbDB.collection("note_info").document().set(noteInfo)
    }

    fun getNoteData(currentUid: String): LiveData<MutableList<NoteInfoModel>> {
        val mutableData = MutableLiveData<MutableList<NoteInfoModel>>()
        FBDocRef.fbDB.collection("note_info").whereEqualTo("uid", currentUid).orderBy("saveTime")
            .get()
            .addOnCompleteListener { task ->
                val noteListData = mutableListOf<NoteInfoModel>()

                noteListData.clear()

                if (task.isSuccessful) {
                    for (dataModel in task.result.documentChanges) {
                        val item = dataModel.document.toObject(NoteInfoModel::class.java)
                        noteListData.add(item)
                        mutableData.value = noteListData
                    }
                }

                noteListData.reverse()
            }

        return mutableData
    }

}