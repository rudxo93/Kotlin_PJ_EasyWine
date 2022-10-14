package com.duran.gyoung_tae_93.pj.easywine.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.InfoItemModel

class InfoRvAdapter(val items: ArrayList<InfoItemModel>): RecyclerView.Adapter<InfoRvAdapter.Viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoRvAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.info_rv_item, parent, false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: InfoRvAdapter.Viewholder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun bindItem(item: InfoItemModel) {
            val title = itemView.findViewById<TextView>(R.id.tv_info_rv_item_title)

            title.text = item.title
        }

    }

}