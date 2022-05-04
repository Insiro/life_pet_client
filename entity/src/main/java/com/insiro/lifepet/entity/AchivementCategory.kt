package com.insiro.lifepet.entity

import com.insiro.lifepet.entity.User
import java.io.Serializable

open class AchivementCategory:Serializable {
    private lateinit var id:String
    private lateinit var name:String
    private lateinit var desc:AchivementCategory

}