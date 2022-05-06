package com.insiro.lifepet.preference



open class DataQuery (var field: Field, var action: Action, var index: Int = -1){

    override fun toString():String{
        return String.format("%s:%s:%d",this.field.str,this.action.str,this.index)
    }
    companion object{
        fun fromString(str:String): DataQuery {
            val splist = str.split(":")
            return DataQuery(Field.valueOf(splist[0]), Action.valueOf(splist[1]),splist[2].toInt())
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