package com.raed.weatherapp.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("base")
    val base: String? = null,
    @SerializedName("clouds")
    val clouds: Clouds? = null,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("coord")
    val coord: Coord? = null,
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: Main? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("sys")
    val sys: Sys? = null,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<WeatherX>? = null,
    @SerializedName("wind")
    val wind: Wind? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Clouds::class.java.classLoader),
        parcel.readInt(),
        parcel.readParcelable(Coord::class.java.classLoader),
        parcel.readLong(),
        parcel.readInt(),
        parcel.readParcelable(Main::class.java.classLoader),
        parcel.readString(),
        parcel.readParcelable(Sys::class.java.classLoader),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(WeatherX),
        parcel.readParcelable(Wind::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(base)
        parcel.writeParcelable(clouds, flags)
        parcel.writeInt(cod)
        parcel.writeParcelable(coord, flags)
        parcel.writeLong(dt)
        parcel.writeInt(id)
        parcel.writeParcelable(main, flags)
        parcel.writeString(name)
        parcel.writeParcelable(sys, flags)
        parcel.writeInt(timezone)
        parcel.writeInt(visibility)
        parcel.writeTypedList(weather)
        parcel.writeParcelable(wind, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherResponse> {
        override fun createFromParcel(parcel: Parcel): WeatherResponse {
            return WeatherResponse(parcel)
        }

        override fun newArray(size: Int): Array<WeatherResponse?> {
            return arrayOfNulls(size)
        }
    }
}