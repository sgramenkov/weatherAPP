package com.example.weather.model

import android.util.Log
import android.widget.Toast
import com.example.weather.RetrofitFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class WeatherModel : IWeatherModel {
    override fun loadData(city:String) {
        var list:Data
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

                            Toast.makeText(Constant.activityContext, "success", Toast.LENGTH_LONG)
                                .show()
                            try {
                                list = response.body()!!
                                val weather: List<WeatherInfo> = list.data
                                val param = weather[0]
                                val activityContext = Constant.activityContext
                                val moreInfoContext = Constant.moreInfoContext
                                val listCitiesContext = Constant.listCitiesContext
                                //val icon = param.weather.icon
                                /*val imgID: Int = context.resources.getIdentifier(
                                    "@drawable/$icon",
                                    null,
                                    activityContext.packageName
                                )*/
                                //val drawable = context.resources.getDrawable(imgID)
                                /* context.cityTV.text = param.city_name
                                 context.tempTv.text = param.temp.toString()
                                 context.weatherIV.setImageDrawable(drawable)
                                 listCitiesContext.searched.alpha = 1.0f
                                 listCitiesContext.cityTV.text = param.city_name
                                 moreInfoContext.cityTV.text = param.city_name
                                 moreInfoContext.moreInfoTV.text =
                                     "Температура: " + param.temp + "\nРассвет: " + param.sunrise + "\nЗакат: " + param.sunset + "\nСкорость ветра: " + param.wind_spd + "\n" + param.weather.description
                                 */listCitiesContext.cityTV.setOnClickListener() {
                                    listCitiesContext.saveData()
                                    activityContext.viewpager.currentItem = 1
                                    listCitiesContext.searchET.setText("")
                                    listCitiesContext.cityTV.text = ""
                                    listCitiesContext.searched.alpha = 0f
                                    listCitiesContext.getData()

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