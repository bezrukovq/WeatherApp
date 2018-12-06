package com.example.vladimir.weatherapp.DAO

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.vladimir.weatherapp.entities.City

@Dao
interface CityDAO {
    @Query("SELECT * FROM city ORDER BY city.name")
    fun getAll(): List<City>

    @Query("delete from city")
    fun deleteAll()

    @Insert
    fun insertCities(city: List<City>)
}
