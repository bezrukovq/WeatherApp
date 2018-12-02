package com.example.vladimir.weatherapp

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface WeatherAPI {
    @GET("find")
    fun getData(@Query("lat") lat: Double,@Query("lon") lon: Double,
                @Query("cnt") amount:Int,@Query("appid") appid:String): Call<CitiesForecast>
}