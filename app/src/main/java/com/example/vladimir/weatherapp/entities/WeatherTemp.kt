package com.example.vladimir.weatherapp.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "weather_temp")
data class WeatherTemp(
    @PrimaryKey var id: Int,

    @ColumnInfo(name = "temp")
    var temp: Double? = null,
    @ColumnInfo(name = "pressure")
    var pressure: Double? = null,
    @ColumnInfo(name = "humidity")
    var humidity: Double? = null
)
