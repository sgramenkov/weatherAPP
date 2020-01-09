package com.example.weather.view

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
import com.example.weather.R
import com.example.weather.adapters.SeenCitiesAdapter
import com.example.weather.Constant
import com.example.weather.model.SeenModel
import com.example.weather.presenter.*
import io.realm.Realm

@Suppress("DEPRECATION")
class CitiesListView() : Fragment(), ICitiesListView {
    var weatherPresenter: IWeatherPresenter? = null
    var citiesListPresenter: ICitiesListPresenter? = null
    var weatherMoreInfoPresenter: IWeatherMoreInfoPresenter? = null
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
        val view = LayoutInflater.from(container!!.context)
            .inflate(R.layout.list_cities_fragment, container, false)
        initViews(view)
        realm = Realm.getDefaultInstance()
        citiesListPresenter = CitiesListPresenter(this)
        val list=citiesListPresenter!!.getData()
        initRecycler(list)
        Constant.listCitiesContext = this
        searchET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                weatherPresenter = WeatherPresenter(Constant.context)
                weatherPresenter!!.loadDataFromSearch(searchET.text.toString())
                weatherMoreInfoPresenter = WeatherMoreInfoPresenter(Constant.moreInfoContext)
                weatherMoreInfoPresenter!!.loadData(searchET.text.toString())

            }
        })
        return view
    }

    fun initViews(view: View) {
        cityTV = view.findViewById(R.id.city_tv)
        searchET = view.findViewById(R.id.search_et)
        recycler = view.findViewById(R.id.cities_recycler)
        searched = view.findViewById(R.id.searched)
    }

    override fun initRecycler(list: ArrayList<SeenModel>) {
        val lm = LinearLayoutManager(context)
        lm.reverseLayout = true
        lm.stackFromEnd = true
        recycler.layoutManager = lm
        recycler.adapter = SeenCitiesAdapter(list)
    }


}


