package com.example.weather.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class SeenModel(var cityName:String?=null):RealmObject()