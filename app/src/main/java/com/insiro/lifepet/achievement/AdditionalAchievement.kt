package com.insiro.lifepet.achievement

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.R
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

        val intent = getIntent()
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

}