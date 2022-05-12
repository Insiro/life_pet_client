package com.insiro.lifepet.authy

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.R
import com.insiro.lifepet.entity.UserFull

class Register : AppCompatActivity() {
    private lateinit var editId: EditText
    private lateinit var editPwd: EditText
    private lateinit var editName: EditText
    private lateinit var editNick: EditText
    private lateinit var editMail: EditText
    private lateinit var editCall: EditText
    private lateinit var registBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_register)
        bind()
    }

    private fun bind() {
        editCall = findViewById(R.id.regist_call)
        editPwd = findViewById(R.id.regist_pwd)
        editId = findViewById(R.id.regist_id)
        editName = findViewById(R.id.regist_name)
        editMail = findViewById(R.id.regist_mail)
        editNick = findViewById(R.id.regist_nick)
        registBtn = findViewById(R.id.register_btn)
        registBtn.setOnClickListener {
            val userFull = UserFull(
                editId.text.toString(),
                editNick.text.toString(),
                editMail.text.toString(),
                editCall.text.toString(),
                editName.text.toString()
            )
            //TODO: request register and check result of api
            var result = false
            val dialogBuilder = AlertDialog.Builder(this)
            if (result) {
                dialogBuilder.setMessage("가입에 성공하였습니다")
                dialogBuilder.create()
                dialogBuilder.show()
                finish()
            } else {
                if (false) {
                    //TODO: check reason is id
                    dialogBuilder.setMessage("ID가 중복되었습니다.")
                    dialogBuilder.create()
                    dialogBuilder.show()
                }
            }
        }
    }
}