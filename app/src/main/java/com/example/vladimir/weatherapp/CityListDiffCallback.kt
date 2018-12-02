package com.example.vladimir.weatherapp

import android.support.v7.util.DiffUtil

class CityListDiffCallback : DiffUtil.ItemCallback<CitiesForecast.City>(){
    override fun areItemsTheSame(old: CitiesForecast.City, new: CitiesForecast.City): Boolean = old.name.equals(new.name)

    override fun areContentsTheSame(old: CitiesForecast.City, new: CitiesForecast.City): Boolean = old.main?.temp==new.main?.temp
}