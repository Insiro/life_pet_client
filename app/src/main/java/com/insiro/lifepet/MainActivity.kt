package com.insiro.lifepet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.authy.Auth
import com.insiro.lifepet.entity.User
import com.insiro.lifepet.preference.ResponseBundleBuilder


class MainActivity : AppCompatActivity() {
    private lateinit var bundleBuilder: ResponseBundleBuilder
    private val users = ArrayList<User>()
    var Id: String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(trace()){//로그인을 한 적이 있다면 (회원 가입을 해서 DB에 id가 저장되어 있다면)
            val intent = Intent(applicationContext, Additional_Achievement::class.java)// Todo: intent를 대쉬보드로 수정
            val bundle = Bundle()
            bundle.putString("id", Id) //Activity간의 ID 교환
            intent.putExtras(bundle)
            startActivity(intent)
        }
        else{//로그인을 한 적이 없다면 auth로 이동하여 로그인 유도
            val intent = Intent(applicationContext, Auth::class.java)
            startActivity(intent)
        }

    }


    fun trace(): Boolean{
        val sh_Pref = getSharedPreferences("Login", MODE_PRIVATE)

        return if (sh_Pref != null && sh_Pref.contains("ID")) {
            val name = sh_Pref.getString("ID", "")
            Id = name!!
            if (name.length == 0) false else true
        } else false
    }

}