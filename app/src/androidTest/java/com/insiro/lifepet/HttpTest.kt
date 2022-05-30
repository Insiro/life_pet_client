package com.insiro.lifepet

import com.insiro.lifepet.entity.UserFull
import junit.framework.TestCase
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.Test


class HttpTest : TestCase() {
    private val client = OkHttpClient()
    private val host = "https://lifepet.insiro.me/api"
    private val test = "test"

    @Test
    fun testAvailableId() {
        val requestUrl =
            host.toHttpUrl().newBuilder().addPathSegment("auth").addPathSegment("availableId")
                .addQueryParameter("id", "test").build()
        val request = Request.Builder().url(requestUrl).build()
        client.newCall(request).execute().use {
            assert(it.code in 200..299) { "statusCode :${it.code}" }
        }
    }

    @Test
    fun testRegister() {
        val requestUrl =
            host.toHttpUrl().newBuilder().addPathSegment("auth").addPathSegment("register").build()
        val params = HashMap<String, String>()
        params["id"] = test
        params["nick_name"] = test
        params["user_name"] = test
        params["email"] = test
        params["call"] = test
        params["pwd"] = test
        val body = Json.encodeToString(params)
        val request = Request.Builder().url(requestUrl)
            .post(body.toRequestBody("application/json".toMediaType())).build()
        client.newCall(request).execute().use {
            assertEquals("statusCode: ${it.code} it mustBe Conflict", it.code, 409)
        }
    }

    @Test
    fun testSign() {
        val requestUrl = host.toHttpUrl().newBuilder()
            .addPathSegment("auth").addPathSegment("sign").build()
        val params = HashMap<String, String>()
        params["id"] = test
        params["pwd"] = test
        val body = Json.encodeToString(params)
        val request = Request.Builder().url(requestUrl)
            .post(body.toRequestBody("application/json".toMediaType())).build()
        client.newCall(request).execute().use {
            assert(it.code == 202) { "statusCode: ${it.code}" }
            val responseBody = it.body!!.string()
            val data: HashMap<String, String> = Json.decodeFromString(responseBody)
            assertEquals(data["user_id"], test)

            val userFull:UserFull = Json.decodeFromString(responseBody)
            assertEquals(userFull.user_id, test)
        }
    }
}