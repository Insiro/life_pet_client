package com.insiro.lifepet.entity


@kotlinx.serialization.Serializable
data class UserFull(
    var user_id:String,
    var nick_name:String,
    var email:String,
    var call:String,
    var pwd: String = "",
    var user_name:String)