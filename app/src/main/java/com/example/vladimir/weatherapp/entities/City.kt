package com.example.vladimir.weatherapp.entities

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("sys")
    var sys: Sys? = null,
    @SerializedName("main")
    var main: WeatherTemp? = null,
    @SerializedName("wind")
    var wind: Wind? = null
)
