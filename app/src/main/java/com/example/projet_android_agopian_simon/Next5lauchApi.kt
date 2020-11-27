package com.example.projet_android_agopian_simon


import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException


object Next5lauchApi {
    private const val TAG = "Next5lauch"
    fun GetNext5lauch() {

        val request: Request = Request.Builder()
            .url("https://launchlibrary.net/1.3/launch/next/5")
            .build()

        val client = OkHttpClient()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            println("Launches: ${response.header("launches")}")
        }
    }
}