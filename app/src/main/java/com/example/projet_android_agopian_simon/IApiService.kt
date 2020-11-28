package com.example.projet_android_agopian_simon

import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface IApiService {
    @GET("launch/next/5")
    fun GetNext5Lauchs() : Observable<LaunchArray>

}

