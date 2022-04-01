package com.raed.weatherapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * Created By Raed Saeed on 01/04/2022
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    fun doSomething() {
        Log.e("TAG", "doSomething: ")
    }
}