package com.example.vladimir.weatherapp.entities


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "city")
data class City(
    @PrimaryKey var id: Int,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String? = null,
    @SerializedName("sys")
    @Embedded(prefix = "sys")
    var sys: Sys? = null,
    @SerializedName("main")
    @Embedded(prefix = "main")
    var main: WeatherTemp? = null,
    @SerializedName("wind")
    @Embedded(prefix = "wind")
    var wind: Wind? = null
)
