package com.insiro.lifepet.achievement

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.insiro.lifepet.R
import com.insiro.lifepet.entity.Achievement

class ListAdapter(val context: Context, val achieveList: ArrayList<Achievement>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.card_item, null)
        val name: TextView = view.findViewById(R.id.item_name)
        val percent: TextView = view.findViewById(R.id.item_sub)

        val achieve = achieveList[position]

        name.text = achieve.id
        percent.text = String.format("%d", achieve.achieve_time * 100 /achieve.target)

        return view
    }


    override fun getItem(position: Int): Any {
        return achieveList[position]
    }


    override fun getItemId(position: Int): Long {
        return 0
    }


    override fun getCount(): Int {
        return achieveList.size
    }
}