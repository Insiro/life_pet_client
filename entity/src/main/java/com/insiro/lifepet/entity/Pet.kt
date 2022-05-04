package com.insiro.lifepet.entity

import java.io.Serializable

open class Pet : Serializable {
    private lateinit var id: String
    private lateinit var category: String
    private var intimacy: Int = 0
    private var exp: Double = 0.0
    private var complexity = 1 //난이도
    private var level = 0


}