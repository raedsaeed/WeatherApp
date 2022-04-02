package com.raed.weatherapp.di

import com.raed.weatherapp.data.repo.WeatherRepo
import com.raed.weatherapp.data.usecase.IWeatherUseCase
import com.raed.weatherapp.data.usecase.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Created By Raed Saeed on 02/04/2022
 */
@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun getWeatherUseCase(repo: WeatherRepo): IWeatherUseCase = WeatherUseCase(repo)
}