package com.example.weather.model

import android.widget.TextView

interface ICitiesListModel {
    fun saveData(cityTV: TextView)
    fun getData(): ArrayList<SeenModel>
}