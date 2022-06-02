package com.insiro.lifepet.authy

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.databinding.AuthRegisterBinding
import com.insiro.lifepet.entity.UserFull
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

class Register : AppCompatActivity() {
    private val client = OkHttpClient()
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
                binding.editPw.text.toString(),
                binding.editName.text.toString()
            )
            val userJson = Json.encodeToString(userFull)

            val request = Request.Builder().url("https://lifepet.insiro.me/api/auth/register")
                .post(userJson.toRequestBody("application/json".toMediaType())).build()
            Thread { client.newCall(request).execute().use { httpResponseHandler(it) } }.start()
        }
    }

    private fun httpResponseHandler(response: Response) {
        val result = response.code in 200..299
        val dialogBuilder = AlertDialog.Builder(this)
        runOnUiThread {
            if (result) {
                dialogBuilder.setMessage("가입에 성공하였습니다")
                dialogBuilder.create()
                dialogBuilder.show()
                finish()
            } else {
                dialogBuilder.setMessage("회원가입에 실패하였습니다")
                dialogBuilder.create()
                dialogBuilder.show()
            }
        }
    }
}