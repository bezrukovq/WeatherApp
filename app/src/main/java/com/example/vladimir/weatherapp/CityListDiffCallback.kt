package com.example.vladimir.weatherapp

import android.support.v7.util.DiffUtil
import com.example.vladimir.weatherapp.entities.City

class CityListDiffCallback : DiffUtil.ItemCallback<City>(){
    override fun areItemsTheSame(old: City, new: City): Boolean = old.name.equals(new.name)

    override fun areContentsTheSame(old: City, new: City): Boolean = old.main?.temp==new.main?.temp
}