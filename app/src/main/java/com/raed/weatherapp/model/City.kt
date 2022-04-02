package com.raed.weatherapp.model

import android.os.Parcel
import android.os.Parcelable
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
) : BaseObject(), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(city)
        parcel.writeString(country)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<City> {
        override fun createFromParcel(parcel: Parcel): City {
            return City(parcel)
        }

        override fun newArray(size: Int): Array<City?> {
            return arrayOfNulls(size)
        }
    }

    fun getName(): String {
        return if (country.isEmpty()) {
            city
        } else {
            "${city}, $country"
        }
    }
}