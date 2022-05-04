package com.insiro.lifepet.entity

import java.io.Serializable
import java.util.*

open class Habit:Serializable {
    private lateinit var id: String
    private lateinit var title:String
    private var target = 0
    private var achive = 0
    private lateinit var date:Date
}