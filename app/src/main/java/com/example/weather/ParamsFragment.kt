package com.example.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weather.model.Constant

class ParamsFragment():Fragment() {
    lateinit var moreInfoTV:TextView
    lateinit var cityTV:TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=LayoutInflater.from(context).inflate(R.layout.params_fragment,container,false)
        moreInfoTV=view.findViewById(R.id.more_info_tv)
        cityTV=view.findViewById(R.id.city_tv)
        Constant.moreInfoContext=this
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

        return view    }
}