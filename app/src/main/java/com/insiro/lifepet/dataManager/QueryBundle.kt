
package com.insiro.lifepet.dataManager

import android.os.Bundle

open class QueryBundle(protected val bundle: Bundle) {
    protected var count = 0
}


open class QueryBundleBuilder() : QueryBundle(Bundle()) {
    fun addQuery(query: Query, data: QueryData? = null): QueryBundleBuilder {
        bundle.putString(queryString(this.count), query.toString())
        this.count++
        if (data != null)
            addData(data)
        return this
    }

    private fun addData(data: QueryData) {
        when (data.field) {
            Field.All, Field.AchieveCate -> return
            else -> bundle.putString(dataString(this.count), data.serialize())
        }
    }

    fun build(): Bundle {
        this.bundle.putInt("count", this.count)
        return bundle
    }
}

open class QueryBundleReader(bundle: Bundle) : QueryBundle(bundle) {
    var max = 0
        private set

    init {
        max = this.bundle.getInt("count")
    }

    fun getQuery(): Query? {
        val qString = bundle.getString(queryString(this.count))
        return if (qString == null) null else Query.fromString(qString)

    }

    fun getData(field: Field): QueryData? {
        val dString = bundle.getString(dataString(this.count))
        return if (dString == null) null else QueryData.deSerialize(dString, field)
    }

    fun next() {
        if (max != count)
            this.count++
    }
}

