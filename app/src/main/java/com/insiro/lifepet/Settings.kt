package com.insiro.lifepet

import android.content.Context
import android.content.Intent
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
import com.insiro.lifepet.dataManager.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class Settings : AppCompatActivity() {
    private lateinit var settingListview: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
    }

    private fun loadBaseSettings(): ArrayList<SettingField> {
        val settingList = ArrayList<SettingField>()
        val signOutMenu = BtnSettingField("로그아웃", this) {
            AlertDialog.Builder(this)
                .setTitle("로그아웃")
                .setPositiveButton("확인") { _, _ ->
                    var intent = Intent(this, DataManager::class.java)
                    val bundle = QueryBundleBuilder()
                        .addQuery(Query(Field.User, Action.Remove))
                        .addQuery(Query(Field.User, Action.Commit))
                        .build()
                    intent.putExtras(bundle)
                    startActivity(intent)
                    intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("취소", null)
                .show()
        }
        settingList.add(signOutMenu)
        return settingList
    }

    private fun bind() {
        setContentView(R.layout.setting_layout)
        settingListview = findViewById(R.id.setting_list)
        val settings = loadBaseSettings()
        settingListview.adapter = SettingsAdapter(settings, this)
        val navBar: BottomNavigationView = findViewById(R.id.bottomNavigation)
        navBar.setOnNavigationItemSelectedListener { NavigationBar(this).onNavigationItemSelected(it) }
    }
}

class SettingsAdapter(private val settings: ArrayList<SettingField>, context: Context) :
    BaseAdapter() {
    private var inflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return settings.size
    }

    override fun getItem(p0: Int): Any {
        return settings[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.card_item, null)
        val name: TextView = view.findViewById(R.id.item_name)
        val desc: TextView = view.findViewById(R.id.item_desc)
        name.text = settings[position].name
        desc.text = settings[position].value
        view.setOnClickListener { settings[position].onClick() }
        return view
    }

}


abstract class SettingField(
    open var value: String,
    val name: String,
    protected val context: AppCompatActivity
) {
    abstract fun load()
    abstract fun onClick()
}

class EditSettingField(name: String, private val pref: SharedPreferences, context: MainActivity) :
    SettingField("", name, context) {
    init {
        load()
    }

    override var value: String = ""
        set(value) {
            val editor: SharedPreferences.Editor = pref.edit()
            editor.putString(name, value)
            editor.apply()
        }

    override fun load() {
        value = pref.getString(name, "").toString()
    }

    override fun onClick() {
        val dBuilder = AlertDialog.Builder(context)
        dBuilder.setTitle(this.name)
        val eText = EditText(context)
        dBuilder.setView(eText)
        dBuilder.setPositiveButton("설정") { _, _ ->
            val va = eText.text.toString()
            value = va
        }
        dBuilder.setNegativeButton("취소", null)
        dBuilder.show()
    }

}

class BtnSettingField(
    name: String,
    context: AppCompatActivity,
    onClickEvent: (BtnSettingField) -> Unit
) :
    SettingField("", name, context) {
    var onClicks = onClickEvent

    override fun load() {}

    override fun onClick() {
        onClicks(this)
    }


}