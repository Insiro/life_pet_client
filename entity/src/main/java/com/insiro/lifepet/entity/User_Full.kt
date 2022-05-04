package com.insiro.lifepet.entity

import com.insiro.lifepet.entity.User
import java.io.Serializable

open class User_Full: User(), Serializable {
    private lateinit var email:String
    private lateinit var call:String
    private lateinit var name:String


}