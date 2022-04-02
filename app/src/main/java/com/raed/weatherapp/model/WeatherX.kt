package com.raed.weatherapp.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class WeatherX(
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("icon")
    val icon: String? = "",
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeString(icon)
        parcel.writeInt(id)
        parcel.writeString(main)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherX> {
        override fun createFromParcel(parcel: Parcel): WeatherX {
            return WeatherX(parcel)
        }

        override fun newArray(size: Int): Array<WeatherX?> {
            return arrayOfNulls(size)
        }
    }
}