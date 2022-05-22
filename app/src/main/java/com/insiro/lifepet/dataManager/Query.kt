@file:Suppress("UNCHECKED_CAST")

package com.insiro.lifepet.dataManager

import com.insiro.lifepet.achievement.Achievement
import com.insiro.lifepet.entity.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


open class Query(var field: Field, var action: Action, var index: Int = 0) {

    override fun toString(): String {
        return String.format("%s:%s:%d", this.field.str, this.action.str, this.index)
    }

    companion object {
        fun fromString(str: String): Query {
            val spList = str.split(":")
            return Query(Field.valueOf(spList[0]), Action.valueOf(spList[1]), spList[2].toInt())
        }
    }
}

enum class Action(val str: String) {
    Commit("Commit"),
    Get("Get"),
    Update("Update"),
    Activate("Activate"),
    Add("Add"),
    Remove("Remove"),
    Sync("Sync");
}

enum class Field(val str: String) {
    All("All"),
    User("User"),
    Habits("Habits"),
    Friends("Friends"),
    Pets("Pets"),
    Achievements("Achievements"),
    AchieveCate("AchieveCate");
}

open class QueryData(val data: Any?, val field: Field, private val isArray: Boolean = false) {
    fun serialize(): String {
        val seData = if (data != null)
            when (this.field) {
                Field.User -> Json.encodeToString(this.data as UserFull)
                Field.Achievements -> Json.encodeToString(this.data as Achievement)
                Field.Pets -> Json.encodeToString(this.data as Pet)
                Field.Habits -> Json.encodeToString(this.data as Habit)
                Field.Friends -> Json.encodeToString(this.data as User)
                else -> throw Exception("Not allowed Field")
            } else "null"
        if (isArray) return arraySerialize()
        return seData
    }

    private fun arraySerialize(): String {
        val seData = when (this.field) {
            Field.AchieveCate -> Json.encodeToString(this.data as ArrayList<AchievementCategory>)
            Field.Achievements -> Json.encodeToString(this.data as ArrayList<Achievement>)
            Field.Pets -> Json.encodeToString(this.data as ArrayList<Pet>)
            Field.Habits -> Json.encodeToString(this.data as ArrayList<Habit>)
            Field.Friends -> Json.encodeToString(this.data as ArrayList<User>)
            else -> throw Exception("Not allowed Field")
        }
        return seData
    }

    companion object {
        fun deSerialize(str: String, field: Field, isArray: Boolean = false): QueryData {
            if (isArray) return arrayDeSerialize(str, field)
            val seData: Any? = if (str == "null") null else
                when (field) {
                    Field.User -> Json.decodeFromString<UserFull>(str)
                    Field.Achievements -> Json.decodeFromString<Achievement>(str)
                    Field.Pets -> Json.decodeFromString<Pet>(str)
                    Field.Habits -> Json.decodeFromString<Habit>(str)
                    Field.Friends -> Json.decodeFromString<User>(str)
                    else -> throw Exception("Not allowed Field")
                }
            return QueryData(seData, field)
        }

        private fun arrayDeSerialize(str: String, field: Field): QueryData {
            val seData: Any = when (field) {
                Field.Achievements -> Json.decodeFromString<ArrayList<Achievement>>(str)
                Field.AchieveCate -> Json.decodeFromString<ArrayList<AchievementCategory>>(str)
                Field.Pets -> Json.decodeFromString<ArrayList<Pet>>(str)
                Field.Habits -> Json.decodeFromString<ArrayList<Habit>>(str)
                Field.Friends -> Json.decodeFromString<ArrayList<User>>(str)
                else -> throw Exception("Not allowed Field")
            }
            return QueryData(seData, field)
        }
    }
}