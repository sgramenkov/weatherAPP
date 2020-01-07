package com.example.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weather.R
import com.example.weather.model.Constant
import com.example.weather.model.WeatherInfo

@Suppress("DEPRECATION")
class MainWeatherFragment() : Fragment(), IWeatherView {
    lateinit var cityTV: TextView
    lateinit var tempTv: TextView
    lateinit var weatherIV: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.main_weather, container, false)

        Constant.context = this
        initViews(view)

        return view
    }


    override fun initViews(view: View) {
        cityTV = view.findViewById(R.id.city_tv)
        tempTv = view.findViewById(R.id.temp_tv)
        weatherIV = view.findViewById(R.id.image_weather)
    }

    override fun show(weatherInfo: WeatherInfo) {
        cityTV.text = weatherInfo.city_name
        tempTv.text = weatherInfo.temp.toString()
        //weatherIV.setImageDrawable(drawable)
       // searched.alpha = 1.0f
        cityTV.text = weatherInfo.city_name
        cityTV.text = weatherInfo.city_name
        /*moreInfoTV.text =
            "Температура: " + param.temp + "\nРассвет: " + param.sunrise + "\nЗакат: " + param.sunset + "\nСкорость ветра: " + param.wind_spd + "\n" + param.weather.description
*/
    }


}