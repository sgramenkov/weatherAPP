package com.example.weather.view

import com.example.weather.WeatherInfo

interface IWeatherMoreInfoView {
    fun bindViews(data: WeatherInfo)
}