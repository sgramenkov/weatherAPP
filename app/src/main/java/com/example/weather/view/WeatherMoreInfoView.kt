package com.example.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weather.R
import com.example.weather.Constant
import com.example.weather.model.WeatherInfo
import com.example.weather.presenter.WeatherMoreInfoPresenter

class WeatherMoreInfoView() : Fragment(), IWeatherMoreInfoView {
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
        /*val service = RetrofitFactory().makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getWeather("Snezhinsk")
            try {
                withContext(Dispatchers.Main) {
                    response.enqueue(object : Callback<Data> {
                        override fun onFailure(call: Call<Data>, t: Throwable) {
                            Log.e("err", t.message!!)
                        }

                        override fun onResponse(
                            call: Call<Data>,
                            response: Response<Data>
                        ) {
                            Toast.makeText(context, "success", Toast.LENGTH_LONG)
                                .show()
                            val list = response.body()!!
                            val weather: List<WeatherInfo> = list.data
                            val param = weather[0]
                            moreInfoTV.text="Рассвет: "+param!!.sunrise+"\nЗакат: "+param.sunset+"\nСкорость ветра: "+param.wind_spd

                        }

                    })

                }

            } catch (err: HttpException) {
                Log.e("Retrofit", "${err.printStackTrace()}")
            }
        }*/

        return view
    }

    fun initViews(view: View) {
        sunriseTV = view.findViewById(R.id.sunrise_tv)
        sunsetTV = view.findViewById<TextView>(R.id.sunset_tv)
        wndSPDTV = view.findViewById<TextView>(R.id.wnd_spd_tv)
        cityTV = view.findViewById(R.id.city_tv)
        weatherTV=view.findViewById<TextView>(R.id.weather_tv)
    }

    override fun bindViews(data: WeatherInfo) {
        cityTV.text = data.city_name
        sunriseTV.text = data!!.sunrise
        sunsetTV.text = data.sunset
        wndSPDTV.text = data.wind_spd
        weatherTV.text=data.weather.description
    }
}