package com.raed.weatherapp.data.repo

import com.raed.weatherapp.BuildConfig
import com.raed.weatherapp.data.local.WeatherDao
import com.raed.weatherapp.data.remote.ApiService
import com.raed.weatherapp.model.City
import com.raed.weatherapp.model.CityWeather
import com.raed.weatherapp.model.WeatherResponse
import javax.inject.Inject


/**
 * Created By Raed Saeed on 02/04/2022
 */
class WeatherRepo @Inject constructor(
    private val api: ApiService,
    private val dao: WeatherDao
) {

    suspend fun getWeatherInfo(city: City): WeatherResponse {
        val response = api.getWeatherInfo(city.getName(), BuildConfig.API_KEY)
        val id = response.id
        val cityWeather = dao.getCity(id)
        cityWeather?.let {
            val weatherList = it.weather?.toMutableList()
            if (weatherList?.last()?.dt != response.dt) {
                weatherList?.add(response)
                dao.insertCity(it)
            }
        } ?: run {
            val newCity = CityWeather(
                id,
                response.name,
                response.sys?.country,
                listOf(response)
            )
            dao.insertCity(newCity)
        }

        return response
    }

    suspend fun getWeatherList(city: City): List<WeatherResponse>? {
        return dao.getCity(city.city).weather
    }
}