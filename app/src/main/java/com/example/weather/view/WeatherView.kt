package com.example.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weather.R
import com.example.weather.Constant
import com.example.weather.WeatherInfo
import com.example.weather.presenter.WeatherPresenter

@Suppress("DEPRECATION")
class WeatherView() : Fragment(), IWeatherView {
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
        val weatherPresenter = WeatherPresenter(Constant.context)
        weatherPresenter!!.loadData("Moscow",null)
        return view
    }

    fun initViews(view: View) {
        cityTV = view.findViewById(R.id.city_tv)
        tempTv = view.findViewById(R.id.temp_tv)
        weatherIV = view.findViewById(R.id.image_weather)
    }

    override fun bindViews(data: WeatherInfo) {
        val icon = data.weather.icon
        val imgID: Int = resources.getIdentifier(
            "@drawable/$icon",
            null,
            getContext()!!.packageName
        )
        val drawable = resources.getDrawable(imgID)
        cityTV.text = data.city_name
        tempTv.text = data.temp.toString()
        weatherIV.setImageDrawable(drawable)
        Constant.activityContext.viewPager.currentItem=1
    }
}