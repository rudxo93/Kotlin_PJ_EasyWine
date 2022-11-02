package com.duran.gyoung_tae_93.pj.easywine.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.NoteInfoModel

class NoteRVAdapter(val context: Context) : RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {
    private var noteList = mutableListOf<NoteInfoModel>()

    fun setListData(data: MutableList<NoteInfoModel>) {
        noteList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_wine_note, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noteInfo: NoteInfoModel = noteList[position]

        holder.title.text = noteInfo.wineName
        holder.drinkDate.text = noteInfo.wineDrinkDate
        holder.noteEtc.text = noteInfo.wineNoteEtc
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.findViewById<ImageView>(R.id.iv_item_rv_note_image)
        val title = itemView.findViewById<TextView>(R.id.tv_item_rv_note_title)
        val drinkDate = itemView.findViewById<TextView>(R.id.tv_item_rv_note_drink_date)
        val noteEtc = itemView.findViewById<TextView>(R.id.tv_item_rv_note_etc)

    }

}