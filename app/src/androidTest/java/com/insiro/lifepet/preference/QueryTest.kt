package com.insiro.lifepet.preference

import com.insiro.lifepet.entity.User
import junit.framework.TestCase
import org.junit.Test

class QueryTest : TestCase() {
    private var users = ArrayList<User>()
    private lateinit var queryData: QueryData

    init {
        for (i in 0..1) {
            users.add(User("test $i", "test $i"))
            queryData = QueryData(users[0], Field.Friends)
        }
    }

    @Test
    fun testQueryDataSerde() {
        val serialized = queryData.serialize()
        val deSerialized = QueryData.deSerialize(serialized, Field.Friends)
        assert(queryData == deSerialized)
    }

    @Test
    fun testQuery() {
        val field = Field.Friends
        val action = Action.Add
        val query = Query(field, action)
        val serialized = query.toString()
        val deSerialized = Query.fromString(serialized)
        assert(field == deSerialized.field)
        assert(action == deSerialized.action)
        assert(deSerialized.index == 0)
    }
}