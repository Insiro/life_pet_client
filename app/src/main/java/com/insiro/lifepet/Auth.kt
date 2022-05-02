package com.insiro.lifepet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.authy.Register

class Auth:AppCompatActivity() {
    lateinit var edit_id:EditText
    lateinit var edit_pwd:EditText
    lateinit var sign_btn: Button
    lateinit var register_btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth)
        bind()
    }
    private fun bind(){
        edit_id = findViewById(R.id.auth_id)
        edit_pwd = findViewById(R.id.auth_pw)
        sign_btn=findViewById(R.id.sign_btn)
        sign_btn.setOnClickListener {

            var id = edit_id.text.toString()
            var pwd = edit_pwd.text.toString()
            //TODO: send HTTP Request for auth
            var status = false
            if (status){
                //TODO: activity move to dashboard
            }
            else{
                Toast.makeText(this, "로그인에 실파해였습니다",Toast.LENGTH_LONG).show()
            }
        }
        register_btn = findViewById(R.id.register_btn)
        register_btn.setOnClickListener {
            var intent = Intent(this, Register::class.java)

            startActivity(intent)
        }
    }
}