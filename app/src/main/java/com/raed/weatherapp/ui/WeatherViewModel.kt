package com.raed.weatherapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raed.weatherapp.data.usecase.IWeatherUseCase
import com.raed.weatherapp.model.City
import com.raed.weatherapp.utils.NetworkViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created By Raed Saeed on 01/04/2022
 */
@HiltViewModel
class WeatherViewModel @Inject constructor(private val useCase: IWeatherUseCase) : ViewModel() {
    private val weatherMutableData = MutableLiveData<NetworkViewState>()
    val weatherLiveData: LiveData<NetworkViewState> = weatherMutableData

    fun getWeatherInfo(city: City) {
        viewModelScope.launch {
            useCase.getWeatherInfo(city).collect {
                weatherMutableData.postValue(it)
            }
        }
    }

    fun getHistoricalWeatherInfo(city: City) {
        viewModelScope.launch {
            useCase.getWeatherInfoList(city).collect {
                weatherMutableData.postValue(NetworkViewState.Success(it))
            }
        }
    }
}