package com.example.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class WeatherInfo(
    @SerializedName("city_name")
    @Expose
    val city_name:String,
    @SerializedName("temp")
    @Expose
    val temp: Double,
    @SerializedName("wind_spd")
    @Expose
    val wind_spd: String,
    @SerializedName("sunrise")
    @Expose
    val sunrise: String,
    @SerializedName("sunset")
    @Expose
    val sunset: String,
    @SerializedName("weather")
    @Expose
    val weather: Weather

)

class Data(
    @SerializedName("data")
    @Expose
    var data: List<WeatherInfo>,
    @SerializedName("count")
    @Expose
    var count: Int
)
class Weather(
    @SerializedName("icon")
    @Expose
    val icon:String,
    @SerializedName("description")
    @Expose
    val description:String
)



