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
    private val dummyCate = AchievementCategory("dummyCate", "dummyCate", "dummy")
    private val achieveList = arrayListOf<Achievement>(
        Achievement("dummy1", "dummyUser", dummyCate, 10, 10),
        Achievement("dummy2", "dummyUser", dummyCate, 10, 10),
        Achievement("dummy3", "dummyUser", dummyCate, 10, 10),
        Achievement("dummy4", "dummyUser", dummyCate, 10, 10),
        Achievement("dummy5", "dummyUser", dummyCate, 10, 10),
        Achievement("dummy6", "dummyUser", dummyCate, 10, 10),
        Achievement("dummy7", "dummyUser", dummyCate, 10, 10),

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.additional_achieve)

        val adapter = ListAdapter(this, achieveList)
        val listView: ListView = findViewById(R.id.additional_achieve_ListView)
        listView.adapter = adapter

        val bundle = intent.extras
        val option = bundle!!.getInt("option")

        //Spinner에 눌린 업적에 따라 label 변경
        title = when (option) {
            0 -> "달성 업적"
            1 -> "미달성 업적"
            2 -> "시작 가능한 업적"
            3 -> "진행중인 업적"
            else -> "업적"
        }


    }

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
                    //Json 형식으로 날라온다

                    /*
                    //TODO: switch를 이용해서 업적 필터링
                    when(option){
                        0 ->
                            //달성 업적
                        1 ->
                        //미달성 업적
                        2 ->
                        //시작 가능한 업적
                        3 ->
                        //진행 중인 업적
                    }
                    */

                    resData.target

                }
            }
        }

    }

}