package com.example.vladimir.weatherapp

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.vladimir.weatherapp.entities.City
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_view.*

class CityHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(item: City) {
        tv_city.text = item.name
        tv_country.text = item.sys?.country
        tv_temp.text= item.main?.temp?.let { Math.round(it-273.15).toString() }
    }
}
