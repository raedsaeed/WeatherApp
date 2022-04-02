package com.raed.weatherapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken

/**
 * Created By Raed Saeed on 02/04/2022
 */
class Converters {
    @TypeConverter
    fun weatherListFromString(value: String?): List<WeatherX>? {
        val listType = object : TypeToken<List<WeatherX>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun stringFromWeatherList(list: List<WeatherX>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun weatherResponseListFromString(value: String?): List<WeatherResponse>? {
        val listType = object : TypeToken<List<WeatherResponse>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun stringFromWeatherResponseList(list: List<WeatherResponse>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}