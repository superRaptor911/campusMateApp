package com.example.campusmate.httpNetwork

import okhttp3.*

class HttpNetwork {

    private val client = OkHttpClient()

    fun callRequest(request : Request, callback : Callback) {
        client.newCall(request).enqueue(callback)
    }

    fun createJsonPostRequest(url : String, jsonString : String) : Request {
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val body = RequestBody.create(mediaType, jsonString)

        return Request.Builder()
            .url(url)
            .post(body)
            .build()
    }

    fun createRequest(url : String, body : FormBody) : Request {
        return Request.Builder()
            .url(url)
            .post(body)
            .build()
    }

    fun createRequest(url : String, args : Map<String, String>) : Request {
        val body = FormBody.Builder()
        for ((key, value) in args) {
            body.add(key, value)
        }
        return Request.Builder()
            .url(url)
            .post(body.build())
            .build()
    }
}
