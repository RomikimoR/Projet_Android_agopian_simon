package com.example.projet_android_agopian_simon

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable

object ApiService  {

    private val apiService = RetrofitClient.getClient().create(IApiService::class.java)
    fun getNext5Launch() : Observable<LaunchArray> {
        return apiService.GetNext5Lauchs();
    }

}

data class Launch(
    @SerializedName("name") val name: String ,
    @SerializedName("net") val date: String
)

data class LaunchArray(
    @SerializedName("launches") val launches:ArrayList<Launch>
)
