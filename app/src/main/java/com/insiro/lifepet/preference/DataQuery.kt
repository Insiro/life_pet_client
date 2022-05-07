package com.insiro.lifepet.preference

import com.insiro.lifepet.entity.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.lang.Exception


open class DataQuery (var field: Field, var action: Action, var index: Int = -1){

    override fun toString():String{
        return String.format("%s:%s:%d",this.field.str,this.action.str,this.index+1)
    }
    companion object{
        fun fromString(str:String): DataQuery {
            val splist = str.split(":")
            return DataQuery(Field.valueOf(splist[0]), Action.valueOf(splist[1]),splist[2].toInt()-1)
        }
    }
}
enum class Action(val str: String){
    Commit("Commit"),
    Get("get"),
    Update("update"),
    Load("load"),
    Add("add");

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
        var seData = when (this.field) {
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
            var seData: Any = when (field) {
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