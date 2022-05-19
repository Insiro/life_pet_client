package com.insiro.lifepet.entity

@kotlinx.serialization.Serializable
data class Pet (
    var id: String,
    var name: String,
    var category: String,
     var intimacy: Int = 0,
     var exp: Double = 0.0,
     var level:Int = 0
)