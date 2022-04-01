package com.raed.weatherapp.data.repo

import com.raed.weatherapp.data.local.CityDao
import com.raed.weatherapp.model.City
import javax.inject.Inject


/**
 * Created By Raed Saeed on 01/04/2022
 */
class CityRepo @Inject constructor(private val dao: CityDao) {
    suspend fun addCity(city: City) {
        dao.insertCity(city)
    }

    suspend fun getCities(searchQuery: String): List<City> {
        return dao.getCities(searchQuery)
    }

    suspend fun getAllCities() = dao.getCities()
}