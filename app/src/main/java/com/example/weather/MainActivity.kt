package com.example.weather

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.weather.adapters.PagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (isOnline(this)){
            viewPager = findViewById(R.id.viewpager)
            val fragmentAdapter =
                PagerAdapter(supportFragmentManager)
            viewPager.offscreenPageLimit=3
            viewPager.adapter = fragmentAdapter
            viewPager.currentItem=1
            val tabLayout: TabLayout = findViewById(R.id.tablayout)
            tabLayout.setupWithViewPager(viewPager)
            Constant.activityContext=this
        }
        else {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(applicationContext, "Connect to the internet", Toast.LENGTH_LONG)
                    .show()
                delay(3000)
                finish()
            }
        }

    }

    @Suppress("DEPRECATION")
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    override fun onBackPressed() {
        if (viewPager.currentItem==0){
            finish()
        }
        viewPager.currentItem=0
    }
}
