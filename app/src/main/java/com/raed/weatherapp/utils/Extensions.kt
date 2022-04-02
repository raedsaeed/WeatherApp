package com.raed.weatherapp

import androidx.navigation.navOptions
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created By Raed Saeed on 01/04/2022
 */

val defaultNavOptions = navOptions {
    anim {
        enter = R.anim.slide_from_right_to_left
        exit = R.anim.slide_in_left
        popEnter = R.anim.slide_in_right
        popExit = R.anim.slide_from_left_to_right
    }
}

fun Long?.getTime(): String {
    return SimpleDateFormat(
        "dd.MM.yyyy - hh:mm",
        Locale.getDefault()
    ).format(Date(this!! * 1000L))
}