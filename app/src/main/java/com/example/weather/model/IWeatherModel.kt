package com.example.weather.model

import android.widget.ProgressBar

interface IWeatherModel {
    fun loadData(city:String,progressBar: ProgressBar?)
    fun loadDataFromSearch(city: String)
}