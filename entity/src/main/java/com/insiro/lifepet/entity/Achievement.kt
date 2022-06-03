package com.insiro.lifepet.entity

@kotlinx.serialization.Serializable
open class Achievement(
    var id: String,
    var user: String,
    var category: AchievementCategory,
    var target: Int = 0,
    var achieved_time: String = ""
)