package com.example.weather.presenter

import android.widget.TextView
import com.example.weather.model.SeenModel
import io.realm.Realm

interface ICitiesListPresenter {
    fun initRecycler(list: ArrayList<SeenModel>)
    fun getData(): ArrayList<SeenModel>
    fun saveData(cityTV:TextView)
}