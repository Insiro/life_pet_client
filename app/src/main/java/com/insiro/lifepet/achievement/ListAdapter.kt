package com.insiro.lifepet.achievement

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.insiro.lifepet.R
import com.insiro.lifepet.entity.Achievement

class ListAdapter(val context: Context, val achieveList: ArrayList<Achievement>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.card_item, null)
        val name: TextView = view.findViewById(R.id.item_name)
        val percent: TextView = view.findViewById(R.id.item_sub)
        val image : ImageView = view.findViewById(R.id.item_image)

        val achieve = achieveList[position]
        val text = "달성률: " + achieve.achieve_time * 100 /achieve.target + "%"

        name.text = achieve.id
        percent.text = text

        if(achieve.category.name.equals("achieve")){
            image.setImageResource(R.drawable.achieve_ilust)
        }
        else if(achieve.category.name.equals("attend")){
            image.setImageResource(R.drawable.logo)
        }
        else if(achieve.category.name.equals("grow_up")){
            image.setImageResource(R.drawable.cat_ilust)
        }
        else{
            ;
        }


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