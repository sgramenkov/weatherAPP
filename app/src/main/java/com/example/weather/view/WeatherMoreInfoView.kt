package com.example.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weather.Constant
import com.example.weather.R
import com.example.weather.WeatherInfo
import com.example.weather.presenter.WeatherMoreInfoPresenter

class WeatherMoreInfoView() : Fragment(), IWeatherMoreInfoView {
    lateinit var imageWeather:ImageView
    lateinit var tempTV: TextView
    lateinit var sunriseTV: TextView
    lateinit var sunsetTV: TextView
    lateinit var wndSPDTV: TextView
    lateinit var cityTV: TextView
    lateinit var weatherTV: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.params_fragment, container, false)
        initViews(view)
        Constant.moreInfoContext = this
        val weatherMoreInfoPresenter = WeatherMoreInfoPresenter(Constant.moreInfoContext)
        weatherMoreInfoPresenter!!.loadData("Moscow")
        return view
    }

    fun initViews(view: View) {
        imageWeather=view.findViewById(R.id.image_weather)
        tempTV = view.findViewById(R.id.temp_tv)
        sunriseTV = view.findViewById(R.id.sunrise_tv)
        sunsetTV = view.findViewById(R.id.sunset_tv)
        wndSPDTV = view.findViewById(R.id.wnd_spd_tv)
        cityTV = view.findViewById(R.id.city_tv)
        weatherTV = view.findViewById(R.id.weather_tv)
    }

    override fun bindViews(data: WeatherInfo) {
        val icon = data.weather.icon
        val imgID: Int = resources.getIdentifier(
            "@drawable/$icon",
            null,
            getContext()!!.packageName
        )
        val drawable = resources.getDrawable(imgID)
        imageWeather.setImageDrawable(drawable)
        tempTV.text = data.temp.toString()
        cityTV.text = data.city_name
        sunriseTV.text = data!!.sunrise
        sunsetTV.text = data.sunset
        wndSPDTV.text = data.wind_spd
        weatherTV.text = data.weather.description
    }
}