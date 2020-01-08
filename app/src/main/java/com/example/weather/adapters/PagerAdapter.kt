package com.example.weather.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.weather.view.CitiesListView
import com.example.weather.view.WeatherView
import com.example.weather.view.WeatherMoreInfoView

@Suppress("DEPRECATION")
class PagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        if (position == 0) return CitiesListView()
        else if (position == 1) return WeatherView()
        else return WeatherMoreInfoView()

    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position === 0) {
            "Cities"
        }
        else if (position==1){
            "Weather"
        }
        else {
            "More Info"
        }
    }
}