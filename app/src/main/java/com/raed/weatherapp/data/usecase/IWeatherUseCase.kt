package com.raed.weatherapp.data.usecase

import com.raed.weatherapp.model.City
import com.raed.weatherapp.model.WeatherResponse
import com.raed.weatherapp.utils.NetworkViewState
import kotlinx.coroutines.flow.Flow


/**
 * Created By Raed Saeed on 02/04/2022
 */
interface IWeatherUseCase {
    fun getWeatherInfo(city: City): Flow<NetworkViewState>

    fun getWeatherInfoList(city: City): Flow<List<WeatherResponse>?>
}