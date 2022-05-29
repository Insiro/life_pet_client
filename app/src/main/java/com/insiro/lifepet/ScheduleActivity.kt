package com.insiro.lifepet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.dataManager.*
import com.insiro.lifepet.databinding.ActivityScheduleBinding
import com.insiro.lifepet.databinding.DialogScheduleEditBinding
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
        scheduleBinding.navigation.setOnNavigationItemSelectedListener {
            NavigationBar(this).onNavigationItemSelected(it)
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

    private fun updateSchedule(result: ActivityResult) = try {
        val bundle = result.data!!.extras
        val responseReader = ResponseBundleReader(bundle!!)
        val data: ArrayList<Habit> = responseReader.getData(true)!!.data as ArrayList<Habit>

        //region add dummy data
        val tempHabit = Habit("habit1", "habit1", 1, 2, "dummy_time")
        data.add(tempHabit)
        //endregion

        this.achieveAdapter.habits = data
        this.achieveAdapter.notifyDataSetChanged()

    } catch (e: Exception) {
        e.printStackTrace()
    }
}

class AchieveAdapter(val context: Context) : BaseAdapter() {
    private val layoutInflater = LayoutInflater.from(context)
    var editMode = false


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
        view.setOnClickListener { editDialog(view, position, editMode) }
        return view
    }


    private fun editDialog(view: View, position: Int, isEditMode: Boolean) {
        val item = habits[position]
        val dialogBinding = DialogScheduleEditBinding.inflate(layoutInflater)
        dialogBinding.editCurrent.setText(item.target.toString())
        dialogBinding.editTitle.setText(item.title)
        dialogBinding.editCurrent.setText(item.acheive.toString())
        dialogBinding.activate.isSelected = item.activated
        dialogBinding.editTitle.isEnabled = isEditMode
        dialogBinding.editTarget.isEnabled = isEditMode
        dialogBinding.activate.isEnabled = isEditMode
        dialogBinding.editTitle.visibility = if (isEditMode) View.VISIBLE else View.GONE
        AlertDialog.Builder(context)
            .setNegativeButton("취소", null)
            .setView(dialogBinding.root)
            .setTitle(if (isEditMode) "수정하기" else item.title)
            .setPositiveButton("저장") { _, _ ->
                val bundle = QueryBundleBuilder()
                    .addQuery(Query(Field.Habits, Action.Activate))
                    .addQuery(Query(Field.Habits, Action.Update))
                    .build()
                val intent = Intent(context, DataManager::class.java)
                intent.putExtras(bundle)
                context.startActivity(intent)
                view.findViewById<TextView>(R.id.item_name).setText(item.target)
                view.findViewById<TextView>(R.id.item_desc).text = "목표 : ${habits[position].target}"
                view.findViewById<TextView>(R.id.item_sub).text = "현재 : ${habits[position].acheive}"
                this.notifyDataSetChanged()
            }.show()

    }

}