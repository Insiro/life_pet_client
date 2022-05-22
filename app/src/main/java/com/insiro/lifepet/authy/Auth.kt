package com.insiro.lifepet.authy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.DashBoardActivity
import com.insiro.lifepet.entity.UserFull
import com.insiro.lifepet.dataManager.*
import com.insiro.lifepet.databinding.AuthBinding

class Auth : AppCompatActivity() {
    lateinit var binding: AuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()
    }

    private fun bind() {
        binding.signBtn.setOnClickListener {

            val id = binding.authId.text.toString()
            val pwd = binding.authPw.text.toString()
            //TODO: send HTTP Request for auth
            var status = false
            if (id == "test" && pwd == "test")
                status = true
            if (status) {
                val user = UserFull(id, "testUser", "user@example.com", "0000000000", "testUser")
                val bundle = QueryBundleBuilder()
                    .addQuery(Query(Field.User, Action.Activate))
                    .addQuery(
                        Query(Field.User, Action.Update),
                        QueryData(user, Field.User)
                    ).addQuery(Query(Field.User, Action.Commit))
                    .build()
                val intent = Intent(this, DashBoardActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
                //TODO: activity move to dashboard
            } else {
                Toast.makeText(this, "로그인에 실파해였습니다", Toast.LENGTH_LONG).show()
            }
        }
        binding.registerBtn.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}