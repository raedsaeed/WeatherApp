package com.raed.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raed.weatherapp.model.City
import kotlinx.coroutines.flow.Flow


/**
 * Created By Raed Saeed on 01/04/2022
 */
@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: City)

    @Query("select * from cities where city like '%' || :search || '%'")
    suspend fun getCities(search: String): List<City>

    @Query("select * from cities")
    fun getCities(): Flow<List<City>>
}