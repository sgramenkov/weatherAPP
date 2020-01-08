package com.example.weather.presenter

import android.widget.ProgressBar
import com.example.weather.model.Data
import com.example.weather.model.WeatherInfo

interface IWeatherPresenter {
    fun loadData(city:String,progressBar: ProgressBar?)
    fun loadDataFromSearch(city:String)
    fun init(data: WeatherInfo)
}