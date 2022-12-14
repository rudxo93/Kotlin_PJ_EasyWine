package com.duran.gyoung_tae_93.pj.easywine.ui.adapter.ready

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.info.InfoTitleModel

class ReadyLVAdapter(val items: MutableList<InfoTitleModel>) : BaseAdapter() {

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
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_lv_ready, parent, false)

        val title = view?.findViewById<TextView>(R.id.lv_info_title)

        title!!.text = items[position].title

        return view
    }

}