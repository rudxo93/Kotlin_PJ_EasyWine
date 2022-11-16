package com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.style

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.type.style.VarietyModel

class StyleRedLVAdapter(val items: MutableList<VarietyModel>) : BaseAdapter() {

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

        layout?.setBackgroundResource(R.drawable.radius_wine_type_background)
        title!!.text = items[position].title
        icon!!.setImageResource(R.drawable.ic_red_wine_icon)

        return v
    }
}