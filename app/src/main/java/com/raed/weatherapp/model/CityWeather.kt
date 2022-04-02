package com.raed.weatherapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created By Raed Saeed on 02/04/2022
 */
@Entity(tableName = "city_weather")
data class CityWeather(
    @PrimaryKey
    val id: Int,

    @NonNull
    val city: String? = "",

    @NonNull
    val country: String? = "",

    var weather: List<WeatherResponse>? = emptyList()
)