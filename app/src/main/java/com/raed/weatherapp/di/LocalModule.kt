package com.raed.weatherapp.di

import android.content.Context
import com.raed.weatherapp.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


/**
 * Created By Raed Saeed on 01/04/2022
 */
@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    fun getDatabase(@ApplicationContext context: Context) = AppDatabase.getInstance(context)

    @Provides
    fun getCityDao(database: AppDatabase) = database.cityDao()

    @Provides
    fun getWeatherDao(database: AppDatabase) = database.weatherDao()
}