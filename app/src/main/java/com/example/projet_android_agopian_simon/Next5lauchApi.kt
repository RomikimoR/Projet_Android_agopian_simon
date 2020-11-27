package com.example.projet_android_agopian_simon


import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.beust.klaxon.Klaxon
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException




object Next5lauchApi {
    private const val TAG = "Next5lauch"
    private val client = OkHttpClient()

    fun run() {
        val request = Request.Builder()
            .url("https://launchlibrary.net/1.3/launch/next/5")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }

                    println(response.body!!.string())

                    val result = Klaxon()
                        .parse<Launch>(response.body.toString())


                    //val asc = Array(5) {response.body  -> (resp).toString() }


                }
            }
        })
    }
}

class Launch(val name: String, val date: String)

