package com.example.vladimir.weatherapp

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.vladimir.weatherapp.DAO.CityDAO
import com.example.vladimir.weatherapp.entities.City
import com.example.vladimir.weatherapp.entities.Sys
import com.example.vladimir.weatherapp.entities.WeatherTemp
import com.example.vladimir.weatherapp.entities.Wind

@Database(entities = arrayOf(City::class, Sys::class,WeatherTemp::class,Wind::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDAO
}
