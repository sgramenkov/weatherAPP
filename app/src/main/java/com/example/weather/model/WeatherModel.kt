package com.example.weather.model

import android.util.Log
import android.widget.ProgressBar
import com.example.weather.Constant
import com.example.weather.Data
import com.example.weather.retrofit.RetrofitFactory
import com.example.weather.WeatherInfo
import com.example.weather.presenter.CitiesListPresenter
import com.example.weather.presenter.ICitiesListPresenter
import com.example.weather.presenter.IWeatherPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

@Suppress("DEPRECATION")
class WeatherModel(val iWeatherPresenter: IWeatherPresenter) : IWeatherModel {
    override fun loadData(city: String, progressBar: ProgressBar?) {
        if (progressBar!=null)
        progressBar.alpha = 1f
        var list: Data
        val service = RetrofitFactory().makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getWeather(city.capitalize())
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

                            try {
                                list = response.body()!!
                                val weather: List<WeatherInfo> = list.data
                                val param = weather[0]
                                iWeatherPresenter.init(param)
                                if (progressBar != null)
                                    progressBar.alpha = 0f
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

    override fun loadDataFromSearch(city: String) {
        var list: Data
        var iCitiesListPresenter:ICitiesListPresenter?=null
        val service = RetrofitFactory().makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getWeather(city.capitalize())
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
                            try {
                                list = response.body()!!
                                val weather: List<WeatherInfo> = list.data
                                val param = weather[0]
                                val listCitiesContext = Constant.listCitiesContext
                                listCitiesContext.cityTV.text = param.city_name
                                listCitiesContext.searched.alpha = 1f
                                listCitiesContext.cityTV.setOnClickListener() {
                                    iCitiesListPresenter=CitiesListPresenter(listCitiesContext)
                                    iCitiesListPresenter!!.saveData(Constant.listCitiesContext.cityTV)
                                    Constant.activityContext.viewPager.currentItem = 1
                                    listCitiesContext.searchET.setText("")
                                    listCitiesContext.cityTV.text = ""
                                    listCitiesContext.searched.alpha = 0f
                                    iWeatherPresenter.init(param)
                                }


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