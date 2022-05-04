package com.insiro.lifepet

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.insiro.lifepet.entity.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


class Preference : AppCompatActivity() {

    private lateinit var data: Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        data = Data(getSharedPreferences("userInfo", MODE_PRIVATE))


    }
    //TODO: return result by intent

}

class Serializer<T : Any>(private var instance: T) {

    fun run(): ByteArray {
        lateinit var serialized: ByteArray
        ByteArrayOutputStream().use { baos ->
            ObjectOutputStream(baos).use { oos ->
                oos.writeObject(this.instance)
                serialized = baos.toByteArray()
            }
        }
        return serialized
    }
}

class Deserializer<T : Any>(pref_data: String?) {
    private lateinit var seData: ByteArray
    private var outObj: T? = null

    init {
        if (pref_data == null)
            outObj = null
        else
            this.seData = pref_data.toByteArray()
    }

    fun run(): T? {
        if (outObj == null)
            return null
        ByteArrayInputStream(this.seData).use { bais ->
            ObjectInputStream(bais).use { ois ->
                this.outObj = ois.readObject() as T
            }
        }
        return this.outObj
    }
}

class Data(pref: SharedPreferences) {
    private lateinit var pref: SharedPreferences
    private var editer: SharedPreferences.Editor = pref.edit()
    private var user: User? = null
    private var habits = ArrayList<Habit>()
    private var friends = ArrayList<User>()
    private var pets = ArrayList<Pet>()
    private var achivements = ArrayList<Achivement>()

    init {
        loadData()
    }

    private fun loadData() {
        val userStr = pref.getString("user", null)
        var seTemp: Any?
        if (userStr == null) {
            //TODO: Handle Null User
            return
        }

        this.user = Deserializer<User_Full>(userStr).run()

        seTemp = Deserializer<ArrayList<Pet>>(pref.getString("pet", null)).run()
        this.pets = seTemp ?: ArrayList()

        seTemp = Deserializer<ArrayList<User>>(pref.getString("friends", null)).run()
        this.friends = seTemp ?: ArrayList()

        seTemp = Deserializer<ArrayList<Habit>>(pref.getString("habit", null)).run()
        this.habits = seTemp ?: ArrayList()

        seTemp = Deserializer<ArrayList<Achivement>>(pref.getString("achieve", null)).run()
        this.achivements = seTemp ?: ArrayList()
    }

    fun saveUser() {
        if (this.user != null)
            this.editer.putString("user", Serializer(this.user!!).run().toString())
        else this.editer.remove("user")
    }
    //TODO: save fields

}
