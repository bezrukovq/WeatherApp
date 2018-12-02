package com.example.vladimir.weatherapp

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimir.weatherapp.entities.City
import kotlinx.android.synthetic.main.item_view.view.*

open class CityAdapter(diffCallback: DiffUtil.ItemCallback<City>, private var callbackItem: CallbackItem) : ListAdapter<City, CityAdapter.CityHolder>(diffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return CityHolder(view)
    }
    override fun onBindViewHolder(viewHolder: CityHolder, position: Int) {
        viewHolder.itemView.tv_city.text = getItem(position).name
        viewHolder.itemView.setOnClickListener { callbackItem.openCity(position) }
        viewHolder.itemView.tv_country.text = getItem(position).sys?.country
        viewHolder.itemView.tv_temp.text= getItem(position).main?.temp?.let { Math.round(it-273.15).toString() }
    }
    inner class CityHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}
