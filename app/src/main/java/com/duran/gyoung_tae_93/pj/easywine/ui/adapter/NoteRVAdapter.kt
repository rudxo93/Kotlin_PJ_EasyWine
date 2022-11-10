package com.duran.gyoung_tae_93.pj.easywine.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.NoteInfoModel

class NoteRVAdapter(val context: Context) : RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {
    private var noteList = mutableListOf<NoteInfoModel>()

    private lateinit var itemClickListner: ItemClickListener

    private lateinit var itemFavoriteClickListner: ItemFavoriteClickListener

    fun setListData(data: MutableList<NoteInfoModel>) {
        noteList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_wine_note, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(noteList[position])
        Glide.with(holder.itemView.context).load(noteList[position].imageUrl).into(holder.image)

        holder.content.setOnClickListener {
            itemClickListner.onClick(it, position, noteList[position].imageUrl)
        }

        holder.favorite.setOnClickListener {
            itemFavoriteClickListner.onClick(it, position, noteList[position].imageUrl)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val drinkDate: TextView = itemView.findViewById(R.id.tv_item_rv_note_drink_date)
        private val noteEtc: TextView = itemView.findViewById(R.id.tv_item_rv_note_etc)
        val content: LinearLayout = itemView.findViewById(R.id.linear_item_rv_content)
        val image: ImageView = itemView.findViewById(R.id.iv_item_rv_note_image)
        val title: TextView = itemView.findViewById(R.id.tv_item_rv_note_title)
        val favorite: CheckBox = itemView.findViewById(R.id.iv_item_rv_note_favorite)

        fun onBind(data: NoteInfoModel) {
            title.text = data.wineName
            drinkDate.text = data.wineDrinkDate
            noteEtc.text = data.wineNoteEtc

            favorite.isChecked = data.isChecked != 0

        }

    }

    /**
     *  WineNote Click
     */
    interface ItemClickListener {
        fun onClick(view: View, position: Int, imageUrl: String?)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

    /**
     *  Favorite Click
     */
    interface ItemFavoriteClickListener {
        fun onClick(view: View, position: Int, imageUrl: String?)
    }

    fun setItemFavoriteClickListener(itemFavoriteClickListener: ItemFavoriteClickListener) {
        this.itemFavoriteClickListner = itemFavoriteClickListener
    }
}