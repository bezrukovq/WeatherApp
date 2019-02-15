package com.example.vladimir.weatherapp.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "sys")
data class Sys(@PrimaryKey var id: Int,
               @ColumnInfo(name = "country")var country: String? = null)
