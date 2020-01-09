package com.example.weather.model

import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.weather.Constant
import com.example.weather.presenter.ICitiesListPresenter
import io.realm.Realm

class CitiesListModel(val iCitiesListPresenter: ICitiesListPresenter) : ICitiesListModel {
    var realm = Realm.getDefaultInstance()

    override fun saveData(cityTV: TextView) {
        realm.executeTransactionAsync({ bgRealm ->
            bgRealm.insertOrUpdate(
                SeenModel(cityTV.text.toString())
            )
        }, {
            Log.e("Write", "Success write")
            getData ()
        }, {
            Toast.makeText(Constant.activityContext, "Fail write", Toast.LENGTH_SHORT).show()
        })
    }

    override fun getData(): ArrayList<SeenModel> {
        var list: ArrayList<SeenModel> = arrayListOf()
        val realmResult = realm.where(SeenModel::class.java).findAll()
        Log.e("results count : ", realmResult.size.toString() + "")
        Log.e("result", realmResult.indices.toString())
        for (i in realmResult.indices) {
            val tempData = SeenModel(realmResult[i]!!.cityName)
            list.add(tempData)
        }
        iCitiesListPresenter!!.initRecycler(list)
        return list
    }


}