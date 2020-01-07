package com.example.weather.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.weather.CitiesList
import com.example.weather.view.MainWeatherFragment
import com.example.weather.ParamsFragment

@Suppress("DEPRECATION")
class PagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        if (position == 0) return CitiesList()
        else if (position == 1) return MainWeatherFragment()
        else return ParamsFragment()

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