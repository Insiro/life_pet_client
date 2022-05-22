package com.insiro.lifepet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.dataManager.*
import com.insiro.lifepet.databinding.ActivityScheduleBinding
import com.insiro.lifepet.entity.Habit

class ScheduleActivity : AppCompatActivity() {
    private lateinit var scheduleBinding: ActivityScheduleBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var achieveAdapter: AchieveAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scheduleBinding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(scheduleBinding.root)
        achieveAdapter = AchieveAdapter(this)
        scheduleBinding.achieveList.adapter = achieveAdapter
        scheduleBinding.btnPlus.setOnClickListener {

        }
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                updateSchedule(it)
            }
        updateScheduleForUpdate()
    }

    private fun updateScheduleForUpdate() {
        val bundle = QueryBundleBuilder()
            .addQuery(Query(Field.Habits, Action.Activate))
            .addQuery(Query(Field.Habits, Action.Get, -1))
            .build()
        val intent = Intent(this, DataManager::class.java)
        intent.putExtras(bundle)
        activityResultLauncher.launch(intent)
    }

    private fun updateSchedule(result: ActivityResult) {
        try {
            val bundle = result.data!!.extras
            val responseReader = ResponseBundleReader(bundle!!)
            Log.e("size", responseReader.max.toString())
            Log.e("Logggg",responseReader.getData(true).toString())
            val data: ArrayList<Habit> = responseReader.getData(true)!!.data as ArrayList<Habit>

            val tempHabit = Habit("habit1", "habit1", 1, 2, "titititme")

            data.add(tempHabit)


            this.achieveAdapter.habits = data
            this.achieveAdapter.notifyDataSetChanged()

        } catch (e: Exception) {
            Log.e("Error",e.toString())
            e.printStackTrace()
        }
    }
}

class AchieveAdapter(context: Context) : BaseAdapter() {
    private val layoutInflater = LayoutInflater.from(context)
    var habits = ArrayList<Habit>()
    override fun getCount(): Int {
        return habits.size
    }

    override fun getItem(p0: Int): Any {
        return habits[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, v: View?, p2: ViewGroup?): View {
        val view = v ?: layoutInflater.inflate(R.layout.card_item, null)
        val title = view.findViewById<TextView>(R.id.item_name)
        val desc = view.findViewById<TextView>(R.id.item_desc)
        val sub = view.findViewById<TextView>(R.id.item_sub)
        title.text = habits[position].title
        desc.text = "목표 : ${habits[position].target}"
        sub.text = "현재 : ${habits[position].acheive}"
        view.setOnClickListener {

        }
        return view
    }

}