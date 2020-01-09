package com.example.weather.view

import com.example.weather.WeatherInfo


interface IWeatherView {
    fun bindViews(data: WeatherInfo)
}