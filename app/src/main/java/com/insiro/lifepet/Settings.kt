package com.insiro.lifepet

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties


class Settings : AppCompatActivity() {
    private lateinit var settingListview: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_layout)
        settingListview = findViewById(R.id.setting_list)
        settingListview.adapter = SettingsAdapter("settings_bare", this)
    }
}

class SettingsNode(
    val field: KMutableProperty<*>,
    private val adapter: SettingsAdapter,
) {
    var value: String = ""
        set(value) {
            field = value
            adapter.editor.putString(this.field.name, value)
        }
    val id: String
        get() {
            return this.field.name
        }

    init {
        value = adapter.pref.getString(field.name, "").toString()
    }
}


class SettingsAdapter(preferenceName: String, private val context: Context) :
    BaseAdapter() {
    private lateinit var settingsList: ArrayList<SettingsNode>
    private lateinit var settings: SettingField
    private var inflater: LayoutInflater
    val pref: SharedPreferences =
        context.getSharedPreferences(preferenceName, AppCompatActivity.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = pref.edit()

    init {
        for (filed in settings::class.memberProperties.toList()) {
            if (filed is KMutableProperty<*>) {

                val node = SettingsNode(filed, this)
                settingsList.add(node)
            }
        }
        inflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return settingsList.size
    }

    override fun getItem(p0: Int): Any {
        return settingsList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.card_item, null)
        val name: TextView = view.findViewById(R.id.item_name)
        val desc: TextView = view.findViewById(R.id.item_desc)
        name.text = settingsList[position].id
        desc.text = settingsList[position].value
        view.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(context)
            val editText = EditText(context)
            dialogBuilder.setView(editText)
            dialogBuilder.setTitle(name.text.toString())
            dialogBuilder.setPositiveButton("Yes") { _, _ ->
                val txt = editText.text
                settingsList[position].value = txt.toString()
                (context as MainActivity).runOnUiThread {
                    desc.text = txt
                }
            }
        }
        return view
    }

}

class SettingField