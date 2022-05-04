package com.insiro.lifepet.entity

import com.insiro.lifepet.entity.User
import com.insiro.lifepet.entity.AchivementCategory
import java.io.Serializable

open class Achivement:Serializable {
    private lateinit var id:String
    private lateinit var user:String
    private lateinit var category:AchivementCategory
    private var achive_time = 0

}