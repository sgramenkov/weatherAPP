package com.example.weather

import com.example.weather.view.CitiesListView
import com.example.weather.MainActivity
import com.example.weather.view.WeatherView
import com.example.weather.view.WeatherMoreInfoView

class Constant {
    companion object Constant{
        var context= WeatherView()
        var activityContext=MainActivity()
        var moreInfoContext= WeatherMoreInfoView()
        var listCitiesContext= CitiesListView()
    }
}