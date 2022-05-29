package com.insiro.lifepet.authy

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.databinding.AuthRegisterBinding
import com.insiro.lifepet.entity.UserFull

class Register : AppCompatActivity() {
    private lateinit var binding: AuthRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()
    }

    private fun bind() {
        binding.registerBtn.setOnClickListener {
            val userFull = UserFull(
                binding.editId.text.toString(),
                binding.editNick.text.toString(),
                binding.editMail.text.toString(),
                binding.editCall.text.toString(),
                binding.editName.text.toString()
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