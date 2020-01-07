package com.example.weather.view

import android.view.View
import com.example.weather.model.WeatherInfo

interface IWeatherView {
    fun initViews(view: View)
    fun show(weatherInfo: WeatherInfo)
}