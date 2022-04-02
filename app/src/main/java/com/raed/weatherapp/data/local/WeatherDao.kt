package com.raed.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raed.weatherapp.model.CityWeather


/**
 * Created By Raed Saeed on 01/04/2022
 */

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityWeather)

    @Query("select * from city_weather where id =:id")
    suspend fun getCity(id: Int): CityWeather?

    @Query("select * from city_weather where city =:city")
    suspend fun getCity(city: String): CityWeather
}