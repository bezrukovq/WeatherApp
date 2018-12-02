package com.example.vladimir.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.vladimir.weatherapp.entities.City
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), CallbackItem {

    private val appid: String = "56fc6c6cb76c0864b4cd055080568268"
    private var longtitude: Double = 49.1221
    private var lattitude: Double = 55.7887
    private lateinit var appAdapter: CityAdapter
    private lateinit var locationManager: LocationManager
    private lateinit var retrofit: Retrofit
    private lateinit var weatherAPI: WeatherAPI

    companion object {
        lateinit var cityList: List<City>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            Toast.makeText(this, "not granted", Toast.LENGTH_SHORT).show()
            requestPermissionWithRationale()
        } else {
            getCitiesWithGeo()
        }
        retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        weatherAPI = retrofit.create(WeatherAPI::class.java)
        appAdapter = CityAdapter(CityListDiffCallback(), this)
        rv_cities.adapter = appAdapter


        weatherAPI.getData(lattitude, longtitude, 50, appid).enqueue(object : Callback<CitiesForecast> {
            override fun onFailure(call: Call<CitiesForecast>?, t: Throwable?) {
                Log.i("",t.toString())
                Toast.makeText(this@MainActivity, "failed to load data", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<CitiesForecast>?, response: Response<CitiesForecast>?) {
                cityList= response?.body()?.list!!
                appAdapter.submitList(cityList)
            }

        })


    }

    override fun openCity(position: Int) {
        val intent = Intent(this, CityActivity::class.java)
        intent.putExtra("id", position)
        startActivity(intent)
    }

    fun requestPermissionWithRationale() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            val message = "NO"
            Snackbar.make(container, message, Snackbar.LENGTH_LONG)
                .setAction("Allow", { v -> requestPerms() })
                .show()
        } else {
            requestPerms()
        }
    }

    private fun requestPerms() {
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(permissions, 123)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        var allowed = false;
        when (requestCode) {
            123 -> allowed = grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
        if (allowed) getCitiesWithGeo() else requestPermissionWithRationale()
    }

    @SuppressLint("MissingPermission")
    fun getCitiesWithGeo() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        ) {
            val criteria = Criteria()
            criteria.accuracy = Criteria.ACCURACY_FINE
            criteria.isAltitudeRequired = false
            criteria.isBearingRequired = false
            criteria.isCostAllowed = true
            criteria.powerRequirement = Criteria.POWER_LOW
            val provider = locationManager.getBestProvider(criteria, true)
            Toast.makeText(
                this," locationManager.getLastKnownLocation(provider).latitude.toString()", Toast.LENGTH_SHORT
            ).show()
        } else Toast.makeText(this, "noprovider", Toast.LENGTH_SHORT).show()
    }
}
