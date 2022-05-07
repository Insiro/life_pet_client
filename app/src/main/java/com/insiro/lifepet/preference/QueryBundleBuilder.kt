package com.insiro.lifepet.preference

import android.os.Bundle
import com.insiro.lifepet.entity.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.lang.Exception

fun queryString(index: Int): String {
    return String.format("query%d", index)
}

fun dataString(index: Int): String {
    return String.format("data%d", index)
}

class QueryBundleBuilder {
    private val bundle = Bundle()
    private var count = 0
    fun addQuery(query: DataQuery, data: QueryData?) {
        bundle.putString(queryString(this.count), query.toString())
        this.count++
        if (data != null)
            addData(data)
    }

    private fun addData(data: QueryData) {
        when (data.field) {
            Field.All, Field.AchieveCate ->
                return
            else -> {
                bundle.putString(dataString(this.count), data.serialize())
            }
        }
    }

    fun build(): Bundle {
        this.bundle.putInt("count", this.count)
        return bundle
    }
}

class QueryBundleReader(private val bundle: Bundle) {
    private var max = 0
    private var count = 0

    init {
        max = this.bundle.getInt("count")
    }

    fun getQuery(): DataQuery? {
        val qString = bundle.getString(queryString(this.count))
        return if (qString == null) null else DataQuery.fromString(qString)

    }

    fun getData(field: Field): QueryData? {
        val dString = bundle.getString(dataString(this.count))
        return if (dString == null) null else QueryData.deSerialize(dString, field)
    }

    fun getQueryCount(): Int {
        return this.max
    }

    fun next() {
        if (max != count)
            this.count++
    }
}

open class ResponseData(protected var bundle: Bundle) {
    protected var count = 0
    protected var max = 0
}

open class ResponseDataBuilder(private val bundle: Bundle) {
    private var count = 0
    private var max = 0
    fun addData(data: QueryData) {
        bundle.putString(queryString(count), data.field.str)
        bundle.putString(dataString(count), data.serialize())
        count++
    }

    fun nextWithoutData() {
        count++
    }

    fun build() {
        bundle.putInt("count", max)
    }
}

open class ResponseDataReader(bundle: Bundle) : ResponseData(bundle) {
    init {
        this.max = this.bundle.getInt("count")
    }

    fun getData(): QueryData? {
        val fieldStr = bundle.getString(queryString(count)) ?: return null
        val field = Field.valueOf(fieldStr)
        var data = bundle.getString(dataString(count)) ?: return null
        return QueryData.deSerialize(data, field)
    }

    fun next() {
        if (max != count)
            this.count++
    }

}