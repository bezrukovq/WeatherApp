package com.example.vladimir.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.arch.persistence.room.Room
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.vladimir.weatherapp.entities.City
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), CallbackItem {

    private var longtitude: Double = 49.1221
    private var lattitude: Double = 55.7887
    private lateinit var appAdapter: CityAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var weatherAPI: WeatherAPI
    private lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            db = Room.databaseBuilder(
                this.applicationContext,
                AppDatabase::class.java, "database-name"
            ).allowMainThreadQueries()
                .build()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
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
        weatherAPI = retrofit.create(WeatherAPI::class.java)
        appAdapter = CityAdapter(CityListDiffCallback(), this)
        rv_cities.adapter = appAdapter
        getData()
    }

    override fun openCity(position: Int) {
        val intent = Intent(this, CityActivity::class.java)
        intent.putExtra("city", Gson().toJson(cityList?.get(position)))
        startActivity(intent)
    }

    private fun getData() {
        weatherAPI.getData(lattitude, longtitude, 50, appid).enqueue(object : Callback<CitiesForecast> {
            override fun onFailure(call: Call<CitiesForecast>?, t: Throwable?) {
                cityList =db.cityDao().getAll()
                appAdapter.submitList(cityList)
                Log.i("", t.toString())
                Toast.makeText(this@MainActivity, "failed to load data", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<CitiesForecast>?, response: Response<CitiesForecast>) {
                db.cityDao().deleteAll()
                db.cityDao().insertCities(response.body().list!!)
                cityList = response.body().list
                appAdapter.submitList(cityList)
            }
        })
    }

    private fun requestPermissionWithRationale() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            val message = "NO"
            Snackbar.make(container, message, Snackbar.LENGTH_LONG)
                .setAction("Allow") { requestPerms() }
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
        var allowed = false
        when (requestCode) {
            123 -> allowed = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
        if (allowed) getCitiesWithGeo()
    }

    @SuppressLint("MissingPermission")
    fun getCitiesWithGeo() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    lattitude = location.latitude
                    longtitude = location.longitude
                    getData()
                    Toast.makeText(this, "Cities by your location", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to get Location. Current set to KAZAN", Toast.LENGTH_LONG).show()
                }
            }
    }

    companion object {
        var cityList: List<City>? = null
        private const val appid: String = "56fc6c6cb76c0864b4cd055080568268"
        private val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
