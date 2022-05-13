package com.insiro.lifepet

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Settings : AppCompatActivity() {
    lateinit var settingListview: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_layout)
        settingListview = findViewById(R.id.setting_list)
        settingListview.adapter = SettingsAdapter("settings_bare", this)
    }

}

class SettingsNode(val id: String, val title: String, var value: String, val src: Resources)

class SettingsAdapter(preferenceName: String, context: Context) :
    BaseAdapter() {
    private lateinit var settingsList: ArrayList<SettingsNode>
    private lateinit var inflater: LayoutInflater
    private val pref = context.getSharedPreferences(preferenceName, AppCompatActivity.MODE_PRIVATE)
    private val editor = pref.edit()

    init {
        settingsList = ArrayList()
        inflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.card_item, null)
        val img: TextView = view.findViewById(R.id.item_image)
        val name: TextView = view.findViewById(R.id.item_name)
        val desc: TextView = view.findViewById(R.id.item_desc)
        name.text = settingsList[position].title
        desc.text = settingsList[position].title
        return view
    }

}