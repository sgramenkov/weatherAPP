package com.example.weather.presenter

import android.widget.ProgressBar
import com.example.weather.model.IWeatherModel
import com.example.weather.model.WeatherInfo
import com.example.weather.model.WeatherModel
import com.example.weather.view.IWeatherView

class WeatherPresenter(
    val iWeatherView: IWeatherView
) : IWeatherPresenter {
    var iweatherModel: IWeatherModel? = null
    override fun loadData(city: String, progressBar: ProgressBar?) {
        if (progressBar!=null)
        iweatherModel!!.loadData(city, progressBar)
        else iweatherModel!!.loadData(city,null)

    }

    override fun init(data: WeatherInfo) {
        iWeatherView.bindViews(data)

    }

    init {
        if (iweatherModel == null) {
            iweatherModel = WeatherModel(this)
        }
    }

    override fun loadDataFromSearch(city: String) {
        iweatherModel!!.loadDataFromSearch(city)
    }
}