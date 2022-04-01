package com.raed.weatherapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raed.weatherapp.data.repo.CityRepo
import com.raed.weatherapp.model.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created By Raed Saeed on 01/04/2022
 */
@HiltViewModel
class CityViewModel @Inject constructor(private val repo: CityRepo) : ViewModel() {
    val citiesLiveData = MutableLiveData<List<City>>()
    val searchCityLiveData = MutableLiveData<List<City>>()
    fun addNewCity(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.addCity(city)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getCities(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                searchCityLiveData.postValue(repo.getCities(query))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getCities() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.getAllCities().collect {
                    citiesLiveData.postValue(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}