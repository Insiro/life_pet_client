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
import java.text.SimpleDateFormat
import java.util.*

class ScheduleActivity : AppCompatActivity() {
    private lateinit var scheduleBinding: ActivityScheduleBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var updateLauncher: ActivityResultLauncher<Intent>
    private lateinit var achieveAdapter: AchieveAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scheduleBinding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(scheduleBinding.root)
        bind()
        updateScheduleForUpdate()
    }

    private fun bind() {
        achieveAdapter = AchieveAdapter(this)
        scheduleBinding.achieveList.adapter = achieveAdapter
        scheduleBinding.ediBtn.setOnClickListener {
            achieveAdapter.editMode = !achieveAdapter.editMode
            scheduleBinding.header.text = if (achieveAdapter.editMode) "수정모드" else "습관"
        }
        
        scheduleBinding.plusBtn.setOnClickListener {
            val dialogBinding = DialogScheduleEditBinding.inflate(layoutInflater)
            dialogBinding.editCurrent.visibility = View.GONE
            dialogBinding.ccur.visibility = View.GONE
            AlertDialog.Builder(this)
                .setNegativeButton("취소", null)
                .setView(dialogBinding.root)
                .setTitle("새로운 습관")
                .setPositiveButton("저장") { _, _ ->
                    val habit = Habit(
                        this.achieveAdapter.habits.size.toString(),
                        dialogBinding.editTitle.text.toString(),
                        dialogBinding.editTarget.text.toString().toInt(),
                        0,
                        SimpleDateFormat("yyyy-MM-DD").format(Calendar.getInstance().time),
                        dialogBinding.activate.isSelected
                    )
                    val bundle = QueryBundleBuilder()
                        .addQuery(Query(Field.Habits, Action.Activate))
                        .addQuery(Query(Field.Habits, Action.Add), QueryData(habit, Field.Habits))
                        .addQuery(Query(Field.Habits, Action.Commit))
                        .build()
                    val intent = Intent(this, DataManager::class.java)
                    intent.putExtras(bundle)
                    updateLauncher.launch(intent)
                }.show()
        }
        scheduleBinding.navigation.setOnNavigationItemSelectedListener {
            NavigationBar(this).onNavigationItemSelected(it)
        }
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                updateSchedule(it)
            }
        updateLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
                updateScheduleForUpdate()
            }
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
        dialogBinding.editTitle.setText(item.title)
        dialogBinding.editTarget.setText(item.target.toString())
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
                val habit = Habit(
                    position.toString(),
                    dialogBinding.editTitle.text.toString(),
                    dialogBinding.editTarget.text.toString().toInt(),
                    dialogBinding.editCurrent.text.toString().toInt(),
                    item.date,
                    dialogBinding.activate.isSelected
                )
                val bundle = QueryBundleBuilder()
                    .addQuery(Query(Field.Habits, Action.Activate))
                    .addQuery(
                        Query(Field.Habits, Action.Update, position),
                        QueryData(habit,Field.Habits)
                    )
                    .addQuery(Query(Field.Habits, Action.Commit))
                    .build()
                val intent = Intent(context, DataManager::class.java)
                intent.putExtras(bundle)
                (context as ScheduleActivity).updateLauncher.launch(intent)
            }.show()
    }
}