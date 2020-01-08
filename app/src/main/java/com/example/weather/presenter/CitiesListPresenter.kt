package com.example.weather.presenter

import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.adapters.SeenCitiesAdapter
import com.example.weather.model.SeenModel
import com.example.weather.view.ICitiesListView
import io.realm.Realm

class CitiesListPresenter(val iCitiesListView: ICitiesListView, val context: Context) :
    ICitiesListPresenter {
   /* override fun initRecycler(list: ArrayList<SeenModel>) {
        iCitiesListView.initRecycler(list)
    }

     override fun getData(realm: Realm): ArrayList<SeenModel> {
        var list: ArrayList<SeenModel> = arrayListOf()

        val realmResult = realm.where(SeenModel::class.java).findAll()
        Log.e("results count : ", realmResult.size.toString() + "")
        Log.e("result", realmResult.indices.toString())
        for (i in realmResult.indices) {
            val tempData = SeenModel(realmResult[i]!!.cityName)
            list.add(tempData)
        }

        return list
    }*/

/*    override fun saveData(realm: Realm,cityTV:TextView) {
        realm.executeTransactionAsync({ bgRealm ->
            bgRealm.insertOrUpdate(
                SeenModel(cityTV.text.toString())
            )
        }, {
            Toast.makeText(context, "Success write", Toast.LENGTH_SHORT).show()
        }, {
            Toast.makeText(context, "Fail write", Toast.LENGTH_SHORT).show()
        })    }*/
    /* fun saveData(realm: Realm,cityTV:TextView) {
         realm.executeTransactionAsync({ bgRealm ->
             bgRealm.insertOrUpdate(
                 SeenModel(cityTV.text.toString())
             )
         }, {
             Toast.makeText(context, "Success write", Toast.LENGTH_SHORT).show()
         }, {
             Toast.makeText(context, "Fail write", Toast.LENGTH_SHORT).show()
         })

     }*/
}