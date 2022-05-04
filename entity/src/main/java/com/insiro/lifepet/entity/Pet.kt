package com.insiro.lifepet.entity

open class Pet {
    private lateinit var id: String
    private lateinit var category: String
    private var intimacy: Int = 0
    private var exp: Double = 0.0
    private var complexity = 1 //난이도
    private var level = 0


}