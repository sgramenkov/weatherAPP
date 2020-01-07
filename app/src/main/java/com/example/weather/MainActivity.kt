package com.example.weather

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.weather.adapters.PagerAdapter
import com.example.weather.model.Constant
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewpager)
        val fragmentAdapter =
            PagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit=3
        viewPager.adapter = fragmentAdapter
        val tabLayout: TabLayout = findViewById(R.id.tablayout)
        tabLayout.setupWithViewPager(viewPager)
        Constant.activityContext=this
        /*if (isOnline(applicationContext)) {
            val service = RetrofitFactory().makeRetrofitService()
            CoroutineScope(Dispatchers.IO).launch {
                val response = service.getWeather("Snezhinsk")
                try {
                    withContext(Dispatchers.Main) {
                        response.enqueue(object : Callback<Data> {
                            override fun onFailure(call: Call<Data>, t: Throwable) {
                                Log.e("err", t.message!!)
                            }

                            override fun onResponse(
                                call: Call<Data>,
                                response: Response<Data>
                            ) {
                                Toast.makeText(applicationContext, "success", Toast.LENGTH_LONG)
                                    .show()
                                val list = response.body()!!
                                val weather: List<WeatherInfo> = list.data
                                val param = weather[0]
                                val viewPager: ViewPager = findViewById(R.id.viewpager)
                                val fragmentAdapter =
                                    PagerAdapter(supportFragmentManager, param)
                                viewPager.adapter = fragmentAdapter
                                val tabLayout: TabLayout = findViewById(R.id.tablayout)
                                tabLayout.setupWithViewPager(viewPager)
                            }

                        })

                    }

                } catch (err: HttpException) {
                    Log.e("Retrofit", "${err.printStackTrace()}")
                }
            }


        } else {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(applicationContext, "Connect to the internet", Toast.LENGTH_LONG)
                    .show()
                delay(5000)
                finish()
            }
        }*/

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
