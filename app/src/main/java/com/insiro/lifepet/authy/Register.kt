package com.insiro.lifepet.authy

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.R

class Register:AppCompatActivity() {
    lateinit var edit_id:EditText
    lateinit var edit_pwd:EditText
    lateinit var edit_name:EditText
    lateinit var edit_nick:EditText
    lateinit var edit_mail:EditText
    lateinit var edit_call:EditText
    lateinit var regist_btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_register)
        bind()
    }
    private fun bind(){
        edit_call = findViewById(R.id.regist_call)
        edit_pwd = findViewById(R.id.regist_pwd)
        edit_id = findViewById(R.id.regist_id)
        edit_name = findViewById(R.id.regist_name)
        edit_mail = findViewById(R.id.regist_mail)
        edit_nick = findViewById(R.id.regist_nick)
        regist_btn = findViewById(R.id.register_btn)
        regist_btn.setOnClickListener {
            //TODO: request register and check result of api
            var result =false
            if (result){
                val dialog_builder = AlertDialog.Builder(this)
                dialog_builder.setMessage("가입에 성공하였습니다")
                dialog_builder.create()
                dialog_builder.show()
                finish()
            }
            else{
                if (false){
                    //TODO: check reason is id
                    val dialog_builder = AlertDialog.Builder(this)
                    dialog_builder.setMessage("ID가 중복되었습니다.")
                    dialog_builder.create()
                    dialog_builder.show()
                }
            }
        }
    }
}