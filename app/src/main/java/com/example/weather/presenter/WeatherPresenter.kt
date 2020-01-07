package com.example.weather.presenter

import android.util.Log
import android.widget.Toast
import com.example.weather.RetrofitFactory
import com.example.weather.model.Constant
import com.example.weather.model.Data
import com.example.weather.model.IWeatherModel
import com.example.weather.model.WeatherInfo
import com.example.weather.view.IWeatherView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class WeatherPresenter(val iWeatherView: IWeatherView) : IWeatherPresenter {
    override fun loadData(city:String) {
       // val context = Constant.context

    }

    override fun init(weatherInfo:WeatherInfo) {
        iWeatherView.show(weatherInfo)
    }



}