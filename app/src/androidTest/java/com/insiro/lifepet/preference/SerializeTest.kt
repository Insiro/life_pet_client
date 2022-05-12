package com.insiro.lifepet.preference

import com.insiro.lifepet.entity.User
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test


class SerializeTest {
    private var users = ArrayList<User>()

    init {
        for (i in 0..1) {
            users.add(User("test $i","test $i"))
        }
    }
    @Test()
    fun testSingleSerialize(){
        val encoded = Json.encodeToString(users[0])
        val decoded = Json.decodeFromString<User>(encoded)
        assert(decoded == users[0])
    }
    @Test
    fun testListSerialize(){
        val encoded = Json.encodeToString(users)
        print(encoded)
        val data = Json.decodeFromString<ArrayList<User>>(encoded)
        assert (users == data)
    }
}