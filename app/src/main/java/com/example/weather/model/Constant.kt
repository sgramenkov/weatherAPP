package com.example.weather.model

import com.example.weather.CitiesList
import com.example.weather.MainActivity
import com.example.weather.view.MainWeatherFragment
import com.example.weather.ParamsFragment

class Constant {
    companion object Constant{
        var context= MainWeatherFragment()
        var activityContext=MainActivity()
        var moreInfoContext=ParamsFragment()
        var listCitiesContext=CitiesList()
    }
}