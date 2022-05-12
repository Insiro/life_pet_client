package com.insiro.lifepet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter (val context: Context, val UserList: ArrayList<achieve>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_achieve, null)
        val Name: TextView = view.findViewById(R.id.item_achieve_name)
        val Whether: TextView = view.findViewById(R.id.item_achieve_whether)
        val Percent: TextView = view.findViewById(R.id.item_achieve_percent)

        val user = UserList[position]

        Name.text = user.name
        Whether.text = Whether.text
        Percent.text = user.percent

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