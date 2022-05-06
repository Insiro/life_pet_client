package com.insiro.lifepet.entity

@kotlinx.serialization.Serializable
data class Pet (
    var id: String,
    var category: String,
     var intimacy: Int = 0,
     var exp: Double = 0.0,
     var complexity:Int = 1, //난이도
     var level:Int = 0
)