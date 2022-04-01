package com.raed.weatherapp.model

import androidx.annotation.NonNull
import androidx.room.Entity


/**
 * Created By Raed Saeed on 01/04/2022
 */
@Entity(tableName = "cities", primaryKeys = ["city"])
data class City(
    @NonNull
    val city: String = "",

    @NonNull
    val country: String = "",
)