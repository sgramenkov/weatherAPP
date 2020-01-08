package com.example.weather.view

import com.example.weather.model.SeenModel

interface ICitiesListView {
fun initRecycler(list: ArrayList<SeenModel>)
}