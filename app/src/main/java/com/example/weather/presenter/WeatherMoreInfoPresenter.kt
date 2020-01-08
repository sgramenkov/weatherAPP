package com.example.weather.presenter

import com.example.weather.model.IWeatherModel
import com.example.weather.model.IWeatherMoreInfoModel
import com.example.weather.model.WeatherInfo
import com.example.weather.model.WeatherMoreInfoModel
import com.example.weather.view.IWeatherMoreInfoView

class WeatherMoreInfoPresenter(var iWeatherMoreInfoView: IWeatherMoreInfoView) :
    IWeatherMoreInfoPresenter {

    var iWeatherMoreInfoModel: IWeatherMoreInfoModel? = null
    override fun loadData(city: String) {
        iWeatherMoreInfoModel!!.loadData(city)
    }

    override fun init(data: WeatherInfo) {
        iWeatherMoreInfoView!!.bindViews(data)
    }
    init {
        iWeatherMoreInfoModel=WeatherMoreInfoModel(this)
    }
}