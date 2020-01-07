package com.example.weather.presenter

import com.example.weather.model.WeatherInfo

interface IWeatherPresenter {
    fun loadData(city:String)
    fun init(weatherInfo: WeatherInfo)
}