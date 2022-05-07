package com.insiro.lifepet.authy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.R
import com.insiro.lifepet.entity.User
import com.insiro.lifepet.entity.UserFull
import com.insiro.lifepet.preference.*

class Auth : AppCompatActivity() {
    lateinit var edit_id: EditText
    lateinit var edit_pwd: EditText
    lateinit var sign_btn: Button
    lateinit var register_btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth)
        bind()
    }

    private fun bind() {
        edit_id = findViewById(R.id.auth_id)
        edit_pwd = findViewById(R.id.auth_pw)
        sign_btn = findViewById(R.id.sign_btn)
        sign_btn.setOnClickListener {

            val id = edit_id.text.toString()
            val pwd = edit_pwd.text.toString()
            //TODO: send HTTP Request for auth
            val status = false
            if (status) {
                var user = UserFull(id, "testUser", "user@example.com", "0000000000", "testUser")
                val bundle = QueryBundleBuilder()
                    .addQuery(
                        Query(Field.User, Action.Update),
                        QueryData(user, Field.User)
                    ).addQuery(Query(Field.User, Action.Commit))
                    .build()
                val intent = Intent()
                intent.putExtras(bundle)
                startActivity(intent)
                //TODO: activity move to dashboard
            } else {
                Toast.makeText(this, "로그인에 실파해였습니다", Toast.LENGTH_LONG).show()
            }
        }
        register_btn = findViewById(R.id.register_btn)
        register_btn.setOnClickListener {
            val intent = Intent(this, Register::class.java)

            startActivity(intent)
        }
    }
}