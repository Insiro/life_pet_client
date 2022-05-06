package com.insiro.lifepet.entity


@kotlinx.serialization.Serializable
data class Habit (
    var id: String,
    var title:String,
    var target:Int = 0,
    var acheive:Int = 0,
    var date:String,
)