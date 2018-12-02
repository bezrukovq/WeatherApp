package com.example.vladimir.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_city.*

class CityActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        val id:Int = intent.getIntExtra("id",0)
        tv_city.text=MainActivity.cityList[id].name
        tv_country.text=MainActivity.cityList[id].sys?.country
        tv_temp.text=Math.round(MainActivity.cityList[id].main?.temp!! - 273.15).toString()
        tv_wet.text="${tv_wet.text} ${MainActivity.cityList[id].main?.humidity}"
        tv_pressure.text="${tv_pressure.text} ${MainActivity.cityList[id].main?.pressure}"
      //  tv_wdir.text="${tv_wdir.text} ${MainActivity.cityList[id].windDirection}"
    }
}