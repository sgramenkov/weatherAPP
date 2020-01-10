package com.example.weather.retrofit

import com.example.weather.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("current?")
    fun getWeather(@Query("city") city: String, @Query("key") key: String = "4ed272fc08c04e9492f477f5dc55593c"): Call<Data>
}