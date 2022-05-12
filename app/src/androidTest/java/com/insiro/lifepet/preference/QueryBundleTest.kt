package com.insiro.lifepet.preference

import com.insiro.lifepet.entity.User
import junit.framework.TestCase
import org.junit.Test

class QueryBundleTest : TestCase() {
    private val users = ArrayList<User>()

    init {
        for (i in 0..3) {
            users.add(User("test $i", "test $i"))
        }
    }
    @Test
    fun testQueryBundle(){
        val builder = QueryBundleBuilder()
        for (i in 0..3) {
            val query = Query(Field.Friends, Action.Add)
            val queryData = QueryData(users[i], Field.Friends)
            builder.addQuery(query, queryData)
        }
        val queryBundle = builder.build()

        val queryReader = QueryBundleReader(queryBundle)
        val size = queryReader.max
        for (i in 0..size){
            val query = queryReader.getQuery()!!
            val data = queryReader.getData(query.field)!!
            assert( query.action == Action.Add)
            assert (query.field == Field.Friends)
            assert(query.index == 0)
            val receivedData = data.data as User
            assert(receivedData.user_id == users[0].user_id)
        }

    }
}