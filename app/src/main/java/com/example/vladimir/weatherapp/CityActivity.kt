package com.example.vladimir.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.vladimir.weatherapp.entities.City
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_city.*

class CityActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        val strObj:String=intent.getStringExtra("city")
        val city: City = Gson().fromJson(strObj,City::class.java)
        tv_city.text= city.name
        tv_country.text= city.sys?.country
        tv_temp.text= city.main?.temp?.let { Math.round(it-273.15).toString() }
        tv_wet.text="${tv_wet.text} ${city.main?.humidity}"
        tv_pressure.text="${tv_pressure.text} ${city.main?.pressure}"
      //  tv_wdir.text="${tv_wdir.text} $item?....windDirection}"
        //winddirection в запросе не приходит
    }
}
