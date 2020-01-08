package com.example.weather.model

import android.util.Log
import android.widget.Toast
import com.example.weather.RetrofitFactory
import com.example.weather.presenter.IWeatherMoreInfoPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response

class WeatherMoreInfoModel(var iWeatherMoreInfoPresenter: IWeatherMoreInfoPresenter) :
    IWeatherMoreInfoModel {
    override fun loadData(city: String) {
        val service = RetrofitFactory().makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getWeather(city)
            try {
                withContext(Dispatchers.Main) {
                    response.enqueue(object : retrofit2.Callback<Data> {
                        override fun onFailure(call: Call<Data>, t: Throwable) {
                            Log.e("err", t.message!!)
                        }

                        override fun onResponse(
                            call: Call<Data>,
                            response: Response<Data>
                        ) {
                          /*  Toast.makeText(Constant.activityContext, "success", Toast.LENGTH_LONG)
                                .show()*/
                            try {
                                val list = response.body()!!
                                val weather: List<WeatherInfo> = list.data
                                val param = weather[0]
                                iWeatherMoreInfoPresenter.init(param)
                            } catch (err: NullPointerException) {

                            }
                        }

                    })

                }

            } catch (err: HttpException) {
                Log.e("Retrofit", "${err.printStackTrace()}")
            }
        }
    }
}