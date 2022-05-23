package com.insiro.lifepet

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock.sleep
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.authy.Auth
import com.insiro.lifepet.dataManager.*
import com.insiro.lifepet.entity.UserFull

class MainActivity : AppCompatActivity() {
    private lateinit var activityResultRancher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityResultRancher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                responseUser(it)
            }
        val intent = Intent(this, DataManager::class.java)
        val bundle = QueryBundleBuilder()
            .addQuery(Query(Field.User, Action.Activate))
            .addQuery(Query(Field.User, Action.Get))
            .build()
        intent.putExtras(bundle)
        activityResultRancher.launch(intent)

    }

    private fun responseUser(result: ActivityResult) {
        val bundle = result.data?.extras
        var intent = Intent(this, Auth::class.java)
        if (bundle != null) {
            val reader = ResponseBundleReader(bundle)
            val receiveData = reader.getData(false)
            if (receiveData != null) {
                val data = receiveData.data as UserFull?
                if (data != null) {
                    intent = Intent(this, DashBoard::class.java)

                }
            }
        }
        sleep(2000)

        startActivity(intent)
        finish()
        return
    }


}