package com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.duran.gyoung_tae_93.pj.easywine.R

class TypeFoodRVAdapter(private val list: ArrayList<String>) : RecyclerView.Adapter<TypeFoodRVAdapter.ViewHolder>() {

    private lateinit var itemClickListner: TypeFoodRVAdapter.ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeFoodRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_match_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: TypeFoodRVAdapter.ViewHolder, position: Int) {
        holder.title.text = list.get(position)
        holder.layout.setOnClickListener {
            itemClickListner.onClick(it, position)
        }

        when(position) {
            0 -> {
                holder.image.setImageResource(R.drawable.ic_food_steak_icon)
            }
            1 -> {
                holder.image.setImageResource(R.drawable.ic_food_chicken_icon)
            }
            2 -> {
                holder.image.setImageResource(R.drawable.ic_food_fish_icon)
            }
            3 -> {
                holder.image.setImageResource(R.drawable.ic_food_shellfish_icon)
            }
            4 -> {
                holder.image.setImageResource(R.drawable.ic_food_vegetable_icon)
            }
            5 -> {
                holder.image.setImageResource(R.drawable.ic_food_spice_icon)
            }
            6 -> {
                holder.image.setImageResource(R.drawable.ic_food_cheese_icon)
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

    fun setItemClickListener(itemClickListener: TypeFoodRVAdapter.ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

}