package com.insiro.lifepet.authy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.DashBoard
import com.insiro.lifepet.entity.UserFull
import com.insiro.lifepet.dataManager.*
import com.insiro.lifepet.databinding.AuthBinding
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

class Auth : AppCompatActivity() {
    private val client = OkHttpClient()
    private lateinit var binding: AuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()
    }

    private fun bind() {
        binding.signBtn.setOnClickListener {
            val user = SignUser(binding.authId.text.toString(), binding.authPw.text.toString())
            val jsonUser = Json.encodeToString(user)
            val request = Request.Builder().url("https://lifepet.insiro.me/api/auth/sign")
                .post(jsonUser.toRequestBody("application/json".toMediaType())).build()
            Thread { client.newCall(request).execute().use { handleSign(it) } }.start()

        }
        binding.registerBtn.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    private fun handleSign(response: Response) {
        val status = response.code in 200..299
        if (status) {

            val body = response.body!!.string()
            val user: UserFull = Json.decodeFromString(body)
            val bundle = QueryBundleBuilder()
                .addQuery(Query(Field.User, Action.Activate))
                .addQuery(
                    Query(Field.User, Action.Update),
                    QueryData(user, Field.User)
                ).addQuery(Query(Field.User, Action.Commit))
                .build()
            var intent= Intent(this, DataManager::class.java)
            intent.putExtras(bundle)
            startActivity(intent)

            intent = Intent(this, DashBoard::class.java)
            startActivity(intent)
        } else {
            runOnUiThread {
                Toast.makeText(this, "로그인에 실파해였습니다", Toast.LENGTH_LONG).show()
            }
        }
    }
}

@kotlinx.serialization.Serializable
private data class SignUser(val id: String, val pwd: String)
