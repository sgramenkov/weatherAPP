package com.example.weather.view

import android.view.View
import com.example.weather.model.WeatherInfo


interface IWeatherView {
    fun bindViews(data:WeatherInfo)
}