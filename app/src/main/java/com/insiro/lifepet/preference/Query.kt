package com.insiro.lifepet.preference

import com.insiro.lifepet.Achievement
import com.insiro.lifepet.entity.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.lang.Exception


open class Query (var field: Field, var action: Action, var index: Int = 0){

    override fun toString():String{
        return String.format("%s:%s:%d",this.field.str,this.action.str,this.index)
    }
    companion object{
        fun fromString(str:String): Query {
            val spList = str.split(":")
            return Query(Field.valueOf(spList[0]), Action.valueOf(spList[1]),spList[2].toInt())
        }
    }
}
enum class Action(val str: String){
    Commit("Commit"),
    Get("get"),
    Update("update"),
    Load("load"),
    Add("add"),
    Remove("remove"),
    Sync("sync");
}
enum class Field(val str:String){
    All("all"),
    User("user"),
    Habits("habits"),
    Friends("friends"),
    Pets("pets"),
    Achievements("achieves"),
    AchieveCate("achieveCate");
}

open class QueryData(val data: Any, val field: Field) {
    fun serialize(): String {
        val seData = when (this.field) {
            Field.User -> Json.encodeToString(this.data as UserFull)
            Field.Achievements -> Json.encodeToString(this.data as Achievement)
            Field.Pets -> Json.encodeToString(this.data as Pet)
            Field.Habits -> Json.encodeToString(this.data as Habit)
            Field.Friends -> Json.encodeToString(this.data as User)
            else -> throw Exception("Not allowed Field")
        }
        return seData
    }

    companion object {
        fun deSerialize(str: String, field: Field): QueryData {
            val seData: Any = when (field) {
                Field.User -> Json.decodeFromString<UserFull>(str)
                Field.Achievements -> Json.decodeFromString<Achievement>(str)
                Field.Pets -> Json.decodeFromString<Pet>(str)
                Field.Habits -> Json.decodeFromString<Habit>(str)
                Field.Friends -> Json.decodeFromString<User>(str)
                else -> throw Exception("Not allowed Field")
            }
            return QueryData(seData, field)
        }
    }
}