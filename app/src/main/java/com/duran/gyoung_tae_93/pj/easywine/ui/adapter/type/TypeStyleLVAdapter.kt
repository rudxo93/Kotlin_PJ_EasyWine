package com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.type.TypeStyleModel

class TypeStyleLVAdapter(val items: MutableList<TypeStyleModel>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v =
            LayoutInflater.from(parent?.context).inflate(R.layout.item_lv_type_style, parent, false)

        val title = v?.findViewById<TextView>(R.id.tv_type_style_title)
        val icon = v?.findViewById<ImageView>(R.id.iv_type_style_icon)
        val layout = v?.findViewById<ConstraintLayout>(R.id.layout_type_style)

        title!!.text = items[position].title

        when (position) {
            0 -> {
                icon?.setImageResource(R.drawable.ic_sparkling_wine_icon)
                layout?.setBackgroundResource(R.drawable.radius_type_style_white_wine)
            }
            1 -> {
                icon?.setImageResource(R.drawable.ic_white_wine_icon)
                layout?.setBackgroundResource(R.drawable.radius_type_style_white_wine)
            }
            2 -> {
                icon?.setImageResource(R.drawable.ic_white_wine_icon)
                layout?.setBackgroundResource(R.drawable.radius_type_style_white_wine)
            }
            3 -> {
                icon?.setImageResource(R.drawable.ic_white_wine_icon)
                layout?.setBackgroundResource(R.drawable.radius_type_style_white_wine)
            }
            4 -> {
                icon?.setImageResource(R.drawable.ic_white_wine_icon)
                layout?.setBackgroundResource(R.drawable.radius_type_style_rose_wine)
            }
            5 -> {
                icon?.setImageResource(R.drawable.ic_red_wine_icon)
                layout?.setBackgroundResource(R.drawable.radius_wine_type_background)
            }
            6 -> {
                icon?.setImageResource(R.drawable.ic_red_wine_icon)
                layout?.setBackgroundResource(R.drawable.radius_wine_type_background)
            }
            7 -> {
                icon?.setImageResource(R.drawable.ic_red_wine_icon)
                layout?.setBackgroundResource(R.drawable.radius_wine_type_background)
            }
            8 -> {
                icon?.setImageResource(R.drawable.ic_red_wine_icon)
                layout?.setBackgroundResource(R.drawable.radius_wine_type_background)
            }

        }

        return v
    }
}