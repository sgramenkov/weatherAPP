package com.example.weather.presenter

import android.widget.TextView
import android.widget.Toast
import com.example.weather.Constant
import com.example.weather.model.CitiesListModel
import com.example.weather.model.ICitiesListModel
import com.example.weather.model.SeenModel
import com.example.weather.view.ICitiesListView
import io.realm.Realm

class CitiesListPresenter(val iCitiesListView: ICitiesListView) :
    ICitiesListPresenter {
    var iCitiesListModel: ICitiesListModel? = null

    override fun initRecycler(list: ArrayList<SeenModel>) {
        iCitiesListView!!.initRecycler(list)
    }

    override fun getData(): ArrayList<SeenModel> {
        val list = iCitiesListModel!!.getData()
        return list
    }

    override fun saveData(cityTV: TextView) {
        iCitiesListModel!!.saveData(cityTV)
    }

    init {
        if (iCitiesListModel == null) {
            iCitiesListModel = CitiesListModel(this)
        }
    }
}