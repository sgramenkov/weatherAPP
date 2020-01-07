package com.example.weather

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.adapters.SeenCitiesAdapter
import com.example.weather.model.Constant
import com.example.weather.model.Data
import com.example.weather.model.SeenModel
import com.example.weather.model.WeatherInfo
import com.example.weather.presenter.IWeatherPresenter
import com.example.weather.presenter.WeatherPresenter
import com.example.weather.view.IWeatherView
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

@Suppress("DEPRECATION")
class CitiesList() : Fragment() {
    lateinit var searched: LinearLayout
    lateinit var realm: Realm
    lateinit var recycler: RecyclerView
    lateinit var cityTV: TextView
    lateinit var searchET: EditText
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Constant.listCitiesContext = this

        Realm.init(context)
        val view = LayoutInflater.from(container!!.context)
            .inflate(R.layout.list_cities_fragment, container, false)
        cityTV = view.findViewById(R.id.city_tv)
        searchET = view.findViewById(R.id.search_et)
        val config = RealmConfiguration.Builder()
            .name("data.realm")
            .build()
        realm = Realm.getInstance(config)
        recycler = view.findViewById(R.id.cities_recycler)
        searched = view.findViewById(R.id.searched)
        getData()

        searchET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                request(searchET.text.toString())
                //iWeatherPresenter.loadData(searchET.text.toString())
            }
        })


        return view
    }

    fun saveData() {
        realm.executeTransactionAsync({ bgRealm ->
            bgRealm.insertOrUpdate(
                SeenModel(cityTV.text.toString())
            )
        }, {
            Toast.makeText(context, "Success write", Toast.LENGTH_SHORT).show()
            getData()
        }, {
            Toast.makeText(context, "Fail write", Toast.LENGTH_SHORT).show()
        })

    }

    fun getData() {
        var list: ArrayList<SeenModel> = arrayListOf()

        val realmResult = realm.where(SeenModel::class.java).findAll()
        Log.e("results count : ", realmResult.size.toString() + "")
        Log.e("result", realmResult.indices.toString())
        for (i in realmResult.indices) {
            val tempData = SeenModel(realmResult[i]!!.cityName)
            list.add(tempData)
        }
        val lm = LinearLayoutManager(context)
        lm.reverseLayout = true
        lm.stackFromEnd = true
        recycler.layoutManager = lm
        recycler.adapter = SeenCitiesAdapter(list)

    }

    fun request(city: String) {
        val context = Constant.context
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

                            Toast.makeText(getContext(), "success", Toast.LENGTH_LONG)
                                .show()
                            try {
                                val list = response.body()!!
                                val weather: List<WeatherInfo> = list.data
                                val param = weather[0]
                                val activityContext = Constant.activityContext
                                val moreInfoContext = Constant.moreInfoContext
                                val icon = param.weather.icon
                                val imgID: Int = resources.getIdentifier(
                                    "@drawable/$icon",
                                    null,
                                    getContext()!!.packageName
                                )
                                val drawable = resources.getDrawable(imgID)
                                context.cityTV.text = param.city_name
                                context.tempTv.text = param.temp.toString()
                                context.weatherIV.setImageDrawable(drawable)
                                searched.alpha = 1.0f
                                cityTV.text = param.city_name
                                moreInfoContext.cityTV.text = param.city_name
                                moreInfoContext.moreInfoTV.text =
                                    "Температура: " + param.temp + "\nРассвет: " + param.sunrise + "\nЗакат: " + param.sunset + "\nСкорость ветра: " + param.wind_spd + "\n" + param.weather.description
                                cityTV.setOnClickListener() {
                                    saveData()
                                    activityContext.viewpager.currentItem = 1
                                    searchET.setText("")
                                    cityTV.text = ""
                                    searched.alpha = 0f
                                    getData()
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

