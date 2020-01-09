package com.example.weather.presenter

import com.example.weather.WeatherInfo

interface IWeatherMoreInfoPresenter {
    fun loadData(city:String)
    fun init(data: WeatherInfo)
}