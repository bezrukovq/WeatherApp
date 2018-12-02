package com.example.vladimir.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_city.*

class CityActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        val id:Int = intent.getIntExtra("id",0)
        tv_city.text= MainActivity.cityList?.get(id)?.name
        tv_country.text= MainActivity.cityList?.get(id)?.sys?.country
        tv_temp.text= MainActivity.cityList?.get(id)?.main?.temp?.let { Math.round(it-273.15).toString() }
        tv_wet.text="${tv_wet.text} ${MainActivity.cityList?.get(id)?.main?.humidity}"
        tv_pressure.text="${tv_pressure.text} ${MainActivity.cityList?.get(id)?.main?.pressure}"
      //  tv_wdir.text="${tv_wdir.text} ${MainActivity.cityList?.get(id)?....windDirection}"
        //winddirection в запросе не приходит
    }
}
