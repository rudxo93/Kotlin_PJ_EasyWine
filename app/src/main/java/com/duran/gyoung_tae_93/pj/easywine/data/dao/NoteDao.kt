package com.duran.gyoung_tae_93.pj.easywine.data.dao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duran.gyoung_tae_93.pj.easywine.data.model.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.data.model.NoteKeyModel
import com.duran.gyoung_tae_93.pj.easywine.util.FBRef
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class NoteDao {

    fun insertNoteInfo(noteInfo: NoteInfoModel, key: String): Task<Void> {
        return FBRef.noteNote.child(key).setValue(noteInfo)
    }

    fun getNoteData(): LiveData<MutableList<NoteInfoModel>> {
        val mutableData = MutableLiveData<MutableList<NoteInfoModel>>()

        FBRef.noteNote.addValueEventListener(object : ValueEventListener {
            val noteListData = mutableListOf<NoteInfoModel>()
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                noteListData.clear()

                for (dataModel in dataSnapshot.children) {
                    val item = dataModel.getValue(NoteInfoModel::class.java)
                    val itemKey = dataModel.key.toString()
                    Log.e("dddddd", itemKey)
                    noteListData.add(item!!)
                    mutableData.value = noteListData

                }

                noteListData.reverse()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        return mutableData
    }

    fun getNoteKey() {
        val mutableData = MutableLiveData<MutableList<NoteKeyModel>>()
        FBRef.noteNote.addValueEventListener(object  : ValueEventListener{
            val noteKey = mutableListOf<NoteKeyModel>()
            override fun onDataChange(snapshot: DataSnapshot) {
                noteKey.clear()

                for (key in snapshot.children) {

                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

}