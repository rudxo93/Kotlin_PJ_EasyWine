package com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.area

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.type.TypeModel

class TypeAreaLVAdapter(val items: MutableList<TypeModel>) : BaseAdapter() {

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
            LayoutInflater.from(parent?.context).inflate(R.layout.item_lv_type_area, parent, false)

        val title = v?.findViewById<TextView>(R.id.tv_type_area_title)

        title!!.text = items[position].title

        return v
    }

}