package com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.food

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.note.NoteRVAdapter

class TypeWineRVAdapter(private val list: ArrayList<String>) : RecyclerView.Adapter<TypeWineRVAdapter.ViewHolder>() {

    private lateinit var itemClickListner: TypeWineRVAdapter.ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeWineRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_match_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: TypeWineRVAdapter.ViewHolder, position: Int) {
        holder.title.text = list.get(position)
        holder.layout.setOnClickListener {
            itemClickListner.onClick(it, position)
        }

        when(position) {
            0 -> {
                holder.image.setImageResource(R.drawable.ic_match_wine_sparkling)
            }
            1 -> {
                holder.image.setImageResource(R.drawable.ic_match_wine_light_white)
            }
            2 -> {
                holder.image.setImageResource(R.drawable.ic_match_wine_medium_white)
            }
            3 -> {
                holder.image.setImageResource(R.drawable.ic_match_wine_full_white)
            }
            4 -> {
                holder.image.setImageResource(R.drawable.ic_match_wine_rose)
            }
            5 -> {
                holder.image.setImageResource(R.drawable.ic_match_wine_light_red)
            }
            6 -> {
                holder.image.setImageResource(R.drawable.ic_match_wine_medium_red)
            }
            7 -> {
                holder.image.setImageResource(R.drawable.ic_match_wine_full_red)
            }
            8 -> {
                holder.image.setImageResource(R.drawable.ic_match_wine_light_white)
            }
            9 -> {
                holder.image.setImageResource(R.drawable.ic_match_wine_medium_white)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.tv_match_title)
        val image: ImageView = itemView.findViewById(R.id.iv_match_image)
        val layout: ConstraintLayout = itemView.findViewById(R.id.layout_click_area)

    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: TypeWineRVAdapter.ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

}