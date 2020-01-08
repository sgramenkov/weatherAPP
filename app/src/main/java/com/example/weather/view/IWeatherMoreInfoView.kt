package com.example.weather.view

import com.example.weather.model.WeatherInfo

interface IWeatherMoreInfoView {
    fun bindViews(data:WeatherInfo)
}