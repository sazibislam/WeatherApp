package com.sazib.weatherapp.data.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityListRequest(
    @Expose @SerializedName("appid") internal val appid: String? = null,
    @Expose @SerializedName("lat") internal val lat: String? = null,
    @Expose @SerializedName("lon") internal val lon: String? = null,
    @Expose @SerializedName("cnt") internal val cnt: Int? = null
)
