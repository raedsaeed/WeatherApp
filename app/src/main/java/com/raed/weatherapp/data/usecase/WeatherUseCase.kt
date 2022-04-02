package com.raed.weatherapp.data.usecase

import com.raed.weatherapp.data.repo.WeatherRepo
import com.raed.weatherapp.model.City
import com.raed.weatherapp.model.WeatherResponse
import com.raed.weatherapp.utils.NetworkViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


/**
 * Created By Raed Saeed on 02/04/2022
 */
class WeatherUseCase @Inject constructor(private val repo: WeatherRepo) : IWeatherUseCase {
    override fun getWeatherInfo(city: City): Flow<NetworkViewState> = flow {
        emit(NetworkViewState.Loading)
        val response = repo.getWeatherInfo(city)
        emit(NetworkViewState.Success(response))
    }.catch {
        emit(NetworkViewState.Error(it))
        it.printStackTrace()
    }.flowOn(Dispatchers.IO)

    override fun getWeatherInfoList(city: City): Flow<List<WeatherResponse>?> = flow {
        val savedDate = repo.getWeatherList(city)
        emit(savedDate)
    }.catch {
        it.printStackTrace()
    }.flowOn(Dispatchers.IO)
}