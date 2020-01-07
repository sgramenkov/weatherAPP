package com.example.weather

import com.example.weather.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("current?")
    fun getWeather(@Query("city") city: String, @Query("key") key: String = "4ed272fc08c04e9492f477f5dc55593c"): Call<Data>
}