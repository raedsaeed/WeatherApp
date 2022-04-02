package com.raed.weatherapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raed.weatherapp.model.City
import com.raed.weatherapp.model.CityWeather
import com.raed.weatherapp.model.Converters
import javax.inject.Singleton


/**
 * Created By Raed Saeed on 01/04/2022
 */

@Singleton
@Database(entities = [City::class, CityWeather::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    abstract fun cityDao(): CityDao

    companion object {
        private const val DATABASE_NAME = "app.db"
        private val LOCK = Any()
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(LOCK) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }
    }
}