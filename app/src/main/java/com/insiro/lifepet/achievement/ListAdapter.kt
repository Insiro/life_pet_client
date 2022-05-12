package com.insiro.lifepet.achievement

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.insiro.lifepet.R

class ListAdapter (val context: Context, val UserList: ArrayList<achieve>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_achieve, null)
        val name: TextView = view.findViewById(R.id.item_achieve_name)
        val whether: TextView = view.findViewById(R.id.item_achieve_whether)
        val percent: TextView = view.findViewById(R.id.item_achieve_percent)

        val user = UserList[position]

        name.text = user.name
        whether.text = whether.text
        percent.text = user.percent

        return view
    }


    override fun getItem(position: Int): Any {
        return UserList[position]
    }


    override fun getItemId(position: Int): Long {
        return 0
    }


    override fun getCount(): Int {
        return UserList.size
    }
}