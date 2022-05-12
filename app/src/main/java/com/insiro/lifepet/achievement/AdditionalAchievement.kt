package com.insiro.lifepet.achievement

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.R

class AdditionalAchievement: AppCompatActivity() {
    private val achieveList = arrayListOf<achieve>(
        achieve("화장실 가기",true,"100%"),
        achieve("몰랑",true,"100%"),
        achieve("동해물과",true,"100%"),
        achieve("물 마시기",false,"56%"),
        achieve("하루 3끼",false,"79%"),
        achieve("헬로헬로",true,"100%"),
        achieve("아 뭐해야되냐",false,"78%"),
        achieve("이상없이 제대로",true,"100%"),
        achieve("제발",false,"15%")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.additional_achieve)

        val adapter = ListAdapter(this, achieveList)
        val listView : ListView = findViewById(R.id.additional_achieve_ListView)
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