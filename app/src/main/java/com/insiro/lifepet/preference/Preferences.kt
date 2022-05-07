package com.insiro.lifepet.preference

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import com.insiro.lifepet.entity.*


class Preferences : AppCompatActivity() {

    private lateinit var data: Data
    private lateinit var queryReader: QueryBundleReader
    private val sendingDataBuilder = ResponseBundleBuilder(Bundle())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = Data(getSharedPreferences("userInfo", MODE_PRIVATE))
        val reqData = intent.extras
        if (reqData != null) {
            this.queryReader = QueryBundleReader(reqData)
            getRequests()
        }
        sendResult()

    }

    private fun sendResult() {
        val intent = Intent()
        intent.putExtras(sendingDataBuilder.build())
        setResult(0, intent)
        finish()
    }

    private fun getRequests() {
        val count = this.queryReader.getQueryCount()
        for (i: Int in 0..count) {
            val query = this.queryReader.getQuery()
            if (query != null) {
                processQuery(query)
            }
        }
    }

    private fun processQuery(query: Query) {
        when (query.action) {
            Action.Commit -> this.data.commitField(query.field)
            Action.Load -> this.data.loadField(query.field)
            Action.Get -> {
                val result = this.data.getField(query.field, query.index)
                if (result != null)
                    sendingDataBuilder.addData(QueryData(result, query.field))
                sendingDataBuilder.nextWithoutData()
            }
            Action.Update -> {
                val queryData = this.queryReader.getData(query.field) ?: return
                this.data.updateField(query.field, queryData.data, query.index)
            }
            Action.Add -> {
                val queryData = this.queryReader.getData(query.field) ?: return
                this.data.addField(query.field, queryData.data)
            }
            Action.Remove -> this.data.removeField(query.field, query.index)
        }
    }
}

class Data(pref: SharedPreferences) {
    private lateinit var pref: SharedPreferences
    private var editor: SharedPreferences.Editor = pref.edit()
    private var user: UserFull? = null
    private var habits = ArrayList<Habit>()
    private var friends = ArrayList<User>()
    private var pets = ArrayList<Pet>()
    private var achievements = ArrayList<Achievement>()
    private var achievementCategories = ArrayList<AchievementCategory>()

    //region Getter
    fun getField(field: Field, index: Int): Any? {
        when (field) {
            Field.AchieveCate -> return this.achievementCategories
            Field.Achievements -> {
                if (index > this.achievements.size) return null
                return this.achievements[index]
            }
            Field.Habits -> {
                if (index > this.habits.size) return null
                return this.habits[index]
            }
            Field.Pets -> {
                if (index > this.pets.size) return null
                return this.pets[index]
            }
            Field.User -> {
                return this.user
            }
            Field.Friends -> {
                if (index > this.friends.size) return null
                return this.friends[index]
            }
            else -> {
                return null
            }
        }
    }

    //endregion
    //region Load
    private fun loadUser() {
        val userStr = pref.getString(Field.User.str, null) ?: TODO("TODO: Handle Null User")
        this.user = Json.decodeFromString(userStr)
    }

    fun loadField(field: Field) {
        when (field) {
            Field.All -> loadAllData()
            Field.Achievements -> loadAchievements()
            Field.AchieveCate -> loadAchievementsCategories()
            Field.Habits -> loadHabits()
            Field.Pets -> loadPets()
            Field.User -> loadUser()
            Field.Friends -> loadFriends()
        }
    }

    private fun loadPets() {
        val prefStr = pref.getString(Field.Pets.str, null)
        this.pets = if (prefStr == null) ArrayList() else Json.decodeFromString(prefStr)
    }

    private fun loadFriends() {
        val prefStr = pref.getString(Field.Friends.str, null)
        this.friends = if (prefStr == null) ArrayList() else Json.decodeFromString(prefStr)
    }

    private fun loadHabits() {
        val prefStr = pref.getString(Field.Habits.str, null)
        this.habits = if (prefStr == null) ArrayList() else Json.decodeFromString(prefStr)
    }

    private fun loadAchievements() {
        val prefStr = pref.getString(Field.Achievements.str, null)
        this.achievements = if (prefStr == null) ArrayList() else Json.decodeFromString(prefStr)
    }

    private fun loadAchievementsCategories() {
        val prefStr = pref.getString(Field.AchieveCate.str, null)
        this.achievementCategories =
            if (prefStr == null) ArrayList() else Json.decodeFromString(prefStr)

    }

    private fun loadAllData() {
        loadUser()
        if (this.user == null) return
        loadPets()
        loadFriends()
        loadHabits()
        loadAchievements()
        loadAchievementsCategories()
    }

    //endregion
    //region Commit
    //TODO: Upload Data to Server on Commits
    fun commitField(field: Field) {
        when (field) {
            Field.All -> {
                commitAchievements()
                commitHabits()
                commitPets()
                commitUser()
                commitFriends()
            }
            Field.Achievements -> commitAchievements()
            Field.Habits -> commitHabits()
            Field.Pets -> commitPets()
            Field.User -> commitUser()
            Field.Friends -> commitFriends()
            else -> {}
        }
    }
    private fun commitUser() {
        if (this.user != null)
            this.editor.putString("user", Json.encodeToString(this.user))
        else this.editor.remove("user")
    }

    private fun commitPets() {
        this.editor.putString("pet", Json.encodeToString(this.pets))
    }

    private fun commitFriends() {
        this.editor.putString("friends", Json.encodeToString(this.friends))
    }

    private fun commitHabits() {
        this.editor.putString("habit", Json.encodeToString(this.habits))
    }

    private fun commitAchievements() {
        this.editor.putString("achieve", Json.encodeToString(this.achievements))
    }

    //endregion
    //region Update
    fun updateField(field: Field, data: Any, index: Int) {
        when (field) {
            Field.Achievements -> updateAchievements(data as Achievement, index)
            Field.Habits -> updateHabits(data as Habit, index)
            Field.Pets -> updatePets(data as Pet, index)
            Field.User -> updateUser(data as UserFull)
            Field.Friends -> updateFriends(data as User, index)
            else -> {}
        }
    }

    private fun updateUser(user: UserFull) {
        if (this.user == null)
            return
        this.user = user
    }

    private fun updatePets(pet: Pet, index: Int) {
        if (this.pets.size == 0 || this.pets.size < index)
            return
        this.pets[index] = pet
    }

    private fun updateFriends(user: User, index: Int) {
        if (this.friends.size == 0 || this.friends.size < index)
            return
        this.friends[index] = user
    }

    private fun updateHabits(habit: Habit, index: Int) {
        if (this.habits.size == 0 || this.habits.size < index)
            return
        this.habits[index] = habit
    }

    private fun updateAchievements(achievement: Achievement, index: Int) {
        if (this.achievements.size == 0 || this.achievements.size < index)
            return
        this.achievements[index] = achievement
    }

    //endregion
    //region Add
    fun addField(field: Field, data: Any) {
        when (field) {
            Field.AchieveCate -> {}
            Field.Achievements -> this.achievements.add(data as Achievement)
            Field.Habits -> this.habits.add(data as Habit)
            Field.Pets -> this.pets.add(data as Pet)
            Field.User -> {}
            Field.Friends -> this.friends.add(data as User)
            else ->{}
        }
    }

    //endregion
    //region Remove
    fun removeField(field: Field,index: Int){
        when (field) {
            Field.All -> {
                this.achievements = ArrayList()
                this.friends = ArrayList()
                this.habits = ArrayList()
                this.pets = ArrayList()
                this.user = null
            }
            Field.Achievements -> {
                if (index==-1)
                    this.achievements = ArrayList()
                else this.achievements.removeAt(index)
            }
            Field.Habits -> {
                if (index==-1)
                    this.habits = ArrayList()
                else this.habits.removeAt(index)
            }
            Field.Pets -> {
                if (index==-1)
                    this.pets = ArrayList()
                else this.pets.removeAt(index)
            }
            Field.User -> {
                this.user = null
            }
            Field.Friends -> {
                if (index==-1)
                    this.friends = ArrayList()
                else this.friends.removeAt(index)
            }
            else -> {}
        }
    }
    //endregion
    //region Sync
    private fun syncAchieveCates() {
        this.editor.putString("achieveCate", Json.encodeToString(this.achievementCategories))
    }
    //TODO: Sync From Server or File
    //endregion


}
