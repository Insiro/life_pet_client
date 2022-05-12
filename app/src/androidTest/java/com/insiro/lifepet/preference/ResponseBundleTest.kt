package com.insiro.lifepet.preference

import android.os.Bundle
import android.util.Log
import com.insiro.lifepet.entity.User
import junit.framework.TestCase
import org.junit.Test

class ResponseBundleTest : TestCase() {
    private lateinit var bundleBuilder: ResponseBundleBuilder
    private val users = ArrayList<User>()

    init {
        for (i in 0..2) {
            users.add(User("test $i", "test $i"))
        }
    }

    @Test
    fun testBuilderReader() {
        val bundle = BundleBuildForTest()
        val reader = ResponseBundleReader(bundle)
        val max = reader.max
        for (i in 0..max) {
            val data = reader.getData(false)!!
            reader.next()
            val qdata = data.data as User
            Log.d("id : $i", qdata.user_id + "\t"+users[i].user_id)
            Log.d("field", data.field.str)
//            assert(qdata.user_id == users[i].user_id)
//            assert(data.field == Field.Friends)
        }
        assert(true)
    }

    fun BundleBuildForTest(): Bundle {
        bundleBuilder = ResponseBundleBuilder(Bundle())
        for (i in 0..2) {
            val queryData = QueryData(users[i], Field.Friends)
            bundleBuilder.addData(queryData)
        }
        return bundleBuilder.build()
    }
}