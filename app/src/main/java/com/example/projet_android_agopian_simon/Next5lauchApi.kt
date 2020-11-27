package com.example.projet_android_agopian_simon


import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.beust.klaxon.Klaxon
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONException
import java.io.IOException
import org.json.JSONObject
import java.nio.charset.Charset


object Next5lauchApi {
    private const val TAG = "Next5lauch"
    private val client = OkHttpClient()
    var Lauchs: ArrayList<Launch> = ArrayList()

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

                    try {
                        val obj = JSONObject(response.body!!.string())
                        val launchArray = obj.getJSONArray("launches")
                        for (i in 0 until launchArray.length()) {
                            val LaunchDetail = launchArray.getJSONObject(i)
                            Lauchs.add(Launch(LaunchDetail.getString("name"),LaunchDetail.getString("net") ) )
                        }
                    }
                    catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    println(Lauchs)


                     val customAdapter = CustomAdapter(Lauchs)

                }


                }

        })
    }
}

class Launch(val name: String, val date: String)

