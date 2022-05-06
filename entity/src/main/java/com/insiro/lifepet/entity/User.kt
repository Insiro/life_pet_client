package com.insiro.lifepet.entity

import kotlinx.serialization.Serializable

@Serializable
data class User (
    var user_id:String,
    var nick_name:String)

