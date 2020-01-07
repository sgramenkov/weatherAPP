package com.example.weather.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.RetrofitFactory
import com.example.weather.model.Constant
import com.example.weather.model.Constant.Constant.context
import com.example.weather.model.Data
import com.example.weather.model.SeenModel
import com.example.weather.model.WeatherInfo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class SeenCitiesAdapter(val list: ArrayList<SeenModel>) :
    RecyclerView.Adapter<SeenCitiesAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val seenCityTV: TextView = view.findViewById(R.id.city_name_tv)
        val progress:ProgressBar=view.findViewById(R.id.progress)
        fun bind(position: Int) {
            seenCityTV.text = list[position].cityName
            itemView.setOnClickListener() {
                progress.alpha=1f
                request(seenCityTV,progress)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.seen_cities_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun request(seenCityTV: TextView,progress:ProgressBar) {
        val service = RetrofitFactory().makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getWeather(seenCityTV.text.toString())
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

                            Toast.makeText(
                                Constant.activityContext,
                                "success",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            try {
                                val list = response.body()!!
                                val weather: List<WeatherInfo> = list.data
                                val param = weather[0]
                                val activityContext = Constant.activityContext
                                val moreInfoContext = Constant.moreInfoContext
                                val icon = param.weather.icon
                                val imgID: Int = activityContext.resources.getIdentifier(
                                    "@drawable/$icon",
                                    null,
                                    activityContext.packageName
                                )
                                val drawable = activityContext.resources.getDrawable(imgID)
                                context.cityTV.text = param.city_name
                                context.tempTv.text = param.temp.toString()
                                context.weatherIV.setImageDrawable(drawable)
                                moreInfoContext.cityTV.text = param.city_name
                                moreInfoContext.moreInfoTV.text =
                                    "Температура: " + param.temp + "\nРассвет: " + param.sunrise + "\nЗакат: " + param.sunset + "\nСкорость ветра: " + param.wind_spd + "\n" + param.weather.description
                                activityContext.viewpager.currentItem = 1
                                progress.alpha=0f
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