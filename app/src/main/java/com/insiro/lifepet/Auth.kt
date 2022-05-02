package com.insiro.lifepet

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Auth:AppCompatActivity() {
    lateinit var edit_id:EditText
    lateinit var edit_pwd:EditText
    lateinit var sign_btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth)
    }
}