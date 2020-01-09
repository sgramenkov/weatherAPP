package com.example.weather.presenter

import android.widget.ProgressBar
import com.example.weather.WeatherInfo

interface IWeatherPresenter {
    fun loadData(city:String,progressBar: ProgressBar?)
    fun loadDataFromSearch(city:String)
    fun init(data: WeatherInfo)
}