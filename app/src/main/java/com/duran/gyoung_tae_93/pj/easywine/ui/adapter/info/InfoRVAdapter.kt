package com.duran.gyoung_tae_93.pj.easywine.ui.adapter.info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.duran.gyoung_tae_93.pj.easywine.R

class InfoRVAdapter(private val list: ArrayList<String>) : RecyclerView.Adapter<InfoRVAdapter.ViewHolder>() {

    private lateinit var itemClickListner: InfoRVAdapter.ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_wine_info, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: InfoRVAdapter.ViewHolder, position: Int) {
        holder.title.text = list.get(position)
        holder.layout.setOnClickListener {
            itemClickListner.onClick(it, position)
        }

        when(position) {
            0 -> {
                holder.image.setImageResource(R.drawable.ic_search)
            }
            1 -> {
                holder.image.setImageResource(R.drawable.ic_search)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.rv_item_tv_info_title)
        val image: ImageView = itemView.findViewById(R.id.rv_item_iv_info_image)
        val layout: View = itemView.findViewById(R.id.rv_item_info_back)

    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: InfoRVAdapter.ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

}