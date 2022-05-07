package com.insiro.lifepet.preference

import android.os.Bundle

open class ResponseBundle(protected var bundle: Bundle) {
    protected var count = 0
    protected var max = 0
}

open class ResponseBundleBuilder(private val bundle: Bundle) {
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

    fun build(): Bundle {
        bundle.putInt("count", max)
        return bundle
    }
}

open class ResponseBundleReader(bundle: Bundle) : ResponseBundle(bundle) {
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