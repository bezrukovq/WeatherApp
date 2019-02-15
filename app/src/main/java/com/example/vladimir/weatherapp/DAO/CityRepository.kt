package com.example.vladimir.weatherapp.DAO

import com.example.vladimir.weatherapp.entities.City
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CityRepository(val cityDAO: CityDAO) {

    fun deleteAll() =
        Completable.fromAction{cityDAO.deleteAll()}
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getCities() : Single<List<City>> =
        cityDAO.getAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun insertCities(cityList: List<City>){
        Completable.fromAction{cityDAO.insertCities(cityList)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
}