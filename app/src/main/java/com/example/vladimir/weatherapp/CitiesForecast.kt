package com.example.vladimir.weatherapp

import com.example.vladimir.weatherapp.entities.City
import com.google.gson.annotations.SerializedName

data class CitiesForecast(
    @SerializedName("list")
    internal var list: List<City>? = null
)
