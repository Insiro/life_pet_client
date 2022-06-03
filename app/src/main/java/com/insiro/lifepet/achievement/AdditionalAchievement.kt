package com.insiro.lifepet.achievement

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.R
import com.insiro.lifepet.dataManager.*
import com.insiro.lifepet.entity.Achievement
import com.insiro.lifepet.entity.AchievementCategory

class AdditionalAchievement : AppCompatActivity() {
    private val achieve = AchievementCategory("dummyCate", "achieve", "dummy")
    private val grow_up = AchievementCategory("dummyCate", "grow_up", "dummy")
    private val attend = AchievementCategory("dummyCate", "attend", "dummy")

    private val achieveList = arrayListOf<Achievement>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.additional_achieve)

        val option = intent.getIntExtra("option", 0);
        if(option ==0){
            achieveList.add(Achievement("습관 5일 연속 달성하기", "test", achieve, 100, "2022. 04. 16 14:34"))
            achieveList.add(Achievement("5일이상 접속하기", "test", attend, 100, "2022. 04. 16 14:34"))
            achieveList.add(Achievement("10일이상 접속하기", "test", attend, 100, "2022. 04. 21 19:56"))
            achieveList.add(Achievement("20일이상 접속하기", "test", attend, 100, "2022. 05. 1 11:13"))
            achieveList.add(Achievement("30일이상 접속하기", "test", attend, 100, "2022. 05. 11 08:27"))
        }
        else{
            achieveList.add(Achievement("습관 10일 연속 달성하기", "test", achieve, 80, ""))
            achieveList.add(Achievement("습관 20일 연속 달성하기", "test", achieve, 40, ""))
            achieveList.add(Achievement("습관 30일 연속 달성하기", "test", achieve, 27, ""))
            achieveList.add(Achievement("펫 1마리 성장 완료하기", "test", grow_up, 0, ""))
            achieveList.add(Achievement("펫 5마리 성장 완료하기", "test", grow_up, 0, ""))
            achieveList.add(Achievement("펫 10마리 성장 완료하기", "test", grow_up, 0, ""))
            achieveList.add(Achievement("펫 20마리 성장 완료하기", "test", grow_up, 0, ""))

        }

        val adapter = ListAdapter(this, achieveList)
        val listView: ListView = findViewById(R.id.additional_achieve_listView)
        listView.adapter = adapter

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