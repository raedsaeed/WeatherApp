package com.raed.weatherapp.data.remote

import com.raed.weatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created By Raed Saeed on 01/04/2022
 */
interface ApiService {
    @GET("weather")
    suspend fun getWeatherInfo(
        @Query("q") city: String,
        @Query("APPID") apiKey: String
    ): WeatherResponse
}