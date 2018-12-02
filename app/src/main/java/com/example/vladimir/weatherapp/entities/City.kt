package com.example.vladimir.weatherapp.entities

import com.example.vladimir.weatherapp.CitiesForecast
import com.google.gson.annotations.SerializedName

class City {
    @SerializedName("name")
    internal var name: String? = null
    @SerializedName("sys")
    internal var sys: Sys? = null
    @SerializedName("main")
    internal var main: WeatherTemp? = null
    @SerializedName("wind")
    internal var wind: Wind? = null
}