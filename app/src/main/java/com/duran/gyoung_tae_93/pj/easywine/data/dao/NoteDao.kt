package com.duran.gyoung_tae_93.pj.easywine.data.dao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duran.gyoung_tae_93.pj.easywine.data.model.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.NoteRVAdapter
import com.duran.gyoung_tae_93.pj.easywine.util.FBDocRef
import com.google.android.gms.tasks.Task

class NoteDao {

    fun insertNoteInfo(noteInfo: NoteInfoModel): Task<Void> {
        return FBDocRef.fbDB.collection("note_info").document().set(noteInfo)
    }

    fun getNoteData(): LiveData<MutableList<NoteInfoModel>> {
        val mutableData = MutableLiveData<MutableList<NoteInfoModel>>()

        FBDocRef.fbDB.collection("note_info").orderBy("saveTime")
            .addSnapshotListener { value, _ ->
                val noteListData = mutableListOf<NoteInfoModel>()
                /*Log.e("++++++++++++++++++++++++++++++++++++++++++++++++", noteListData.toString())*/

                noteListData.clear()
                for (dataModel in value!!.documentChanges) {
                    val item = dataModel.document.toObject(NoteInfoModel::class.java)
                    noteListData.add(item)
                    mutableData.value = noteListData
                    /*Log.e("============================================================", noteListData.toString())*/
                }

                noteListData.reverse()
            }

        return mutableData
    }

}