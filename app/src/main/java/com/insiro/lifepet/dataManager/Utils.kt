package com.insiro.lifepet.dataManager

fun queryString(index: Int): String {
    return String.format("query%d", index)
}

fun dataString(index: Int): String {
    return String.format("data%d", index)
}