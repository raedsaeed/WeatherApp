package com.raed.weatherapp


/**
 * Created By Raed Saeed on 04/01/2022
 */
sealed class NetworkViewState {
    object Loading : NetworkViewState()
    class Success<T>(val item: T?) : NetworkViewState() where T : Any
    class Error(val error: Throwable) : NetworkViewState()
}