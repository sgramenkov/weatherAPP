package com.example.weather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.Constant
import com.example.weather.model.SeenModel
import com.example.weather.presenter.*

@Suppress("DEPRECATION")
class SeenCitiesAdapter(val list: ArrayList<SeenModel>) :
    RecyclerView.Adapter<SeenCitiesAdapter.ViewHolder>() {
    var weatherPresenter: IWeatherPresenter? = null
    var weatherMoreInfoPresenter: IWeatherMoreInfoPresenter? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val seenCityTV: TextView = view.findViewById(R.id.city_name_tv)
        val progress: ProgressBar = view.findViewById(R.id.progress)
        fun bind(position: Int) {
            seenCityTV.text = list[position].cityName
            itemView.setOnClickListener() {
                weatherPresenter = WeatherPresenter(Constant.context)
                weatherPresenter!!.loadData(list[position].cityName!!, progress)
                weatherMoreInfoPresenter = WeatherMoreInfoPresenter(Constant.moreInfoContext)
                weatherMoreInfoPresenter!!.loadData(list[position].cityName!!)

                //request(seenCityTV,progress)
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

}