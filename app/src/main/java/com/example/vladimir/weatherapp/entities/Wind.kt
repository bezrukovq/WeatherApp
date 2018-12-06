package com.example.vladimir.weatherapp.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "wind_class")
data class Wind(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "speed")
    var speed: Double? = null,
    @ColumnInfo(name = "deg")
    var deg: Double? = null,
    @ColumnInfo(name="gust")
    var gust: Double? = null
)
