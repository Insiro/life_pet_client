package com.insiro.lifepet.achievement

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.R
import com.insiro.lifepet.entity.Achievement
import com.insiro.lifepet.entity.AchievementCategory

class MoreAchievement : AppCompatActivity() {
    private val achieve = AchievementCategory("dummyCate", "achieve", "dummy")
    private val grow_up = AchievementCategory("dummyCate", "grow_up", "dummy")
    private val attend = AchievementCategory("dummyCate", "attend", "dummy")

    private val achieveList = arrayListOf<Achievement>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_achievement)

        val option = intent.getIntExtra("option", 0);
        if(option ==0){
            achieveList.add(Achievement("습관 5일 연속 달성하기", "test", achieve, 5, 5))
            achieveList.add(Achievement("5일이상 접속하기", "test", attend, 5, 5))
            achieveList.add(Achievement("10일이상 접속하기", "test", attend, 10, 10))
            achieveList.add(Achievement("20일이상 접속하기", "test", attend, 20, 20))
            achieveList.add(Achievement("30일이상 접속하기", "test", attend, 30, 30))
        }
        else{
            achieveList.add(Achievement("습관 10일 연속 달성하기", "test", achieve, 10, 9))
            achieveList.add(Achievement("습관 20일 연속 달성하기", "test", achieve, 20, 9))
            achieveList.add(Achievement("습관 30일 연속 달성하기", "test", achieve, 30, 9))
            achieveList.add(Achievement("펫 1마리 성장 완료하기", "test", grow_up, 1, 0))
            achieveList.add(Achievement("펫 5마리 성장 완료하기", "test", grow_up, 5, 0))
            achieveList.add(Achievement("펫 10마리 성장 완료하기", "test", grow_up, 10, 0))
            achieveList.add(Achievement("펫 20마리 성장 완료하기", "test", grow_up, 20, 0))

        }

        val adapter = ListAdapter(this, achieveList)
        val listView: ListView = findViewById(R.id.additional_achieve_listView)
        listView.adapter = adapter

        listView.setOnItemClickListener { adapterView, view, position, id ->
            val intent = Intent(this, DetailAchievement::class.java)
            intent.putExtra("name", achieveList[position].id)
            intent.putExtra("rate", (achieveList[position].achieve_time * 100/achieveList[position].target).toString())
            startActivity(intent)
        }

    }

    /*
    fun loadAchieveList(option: Int) {
        val bundle = QueryBundleBuilder().addQuery(Query(Field.Achievements, Action.Get)).build()
        val intent = Intent(this, DataManager::class.java)
        intent.putExtras(bundle)
        var activityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { getResult(it, option) }
    }


    private fun getResult(Result: ActivityResult, option: Int) {
        if (Result.data != null) {
            val bundle = Result.data!!.extras
            if (bundle != null) {
                val reader = ResponseBundleReader(bundle)
                val resDataWrapper = reader.getData(false)

                if (resDataWrapper != null) {
                    val resData = resDataWrapper.data as Achievement
                    reader.next()
                    resData.target

                }
            }
        }

    }

     */

}