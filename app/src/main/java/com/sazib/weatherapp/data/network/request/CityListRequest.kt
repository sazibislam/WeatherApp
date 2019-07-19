package com.sazib.weatherapp.data.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityListRequest(
    @Expose @SerializedName("appid") internal val appid: String? = "e384f9ac095b2109c751d95296f8ea76",
    @Expose @SerializedName("cnt") internal val cnt: Int? = 50,
    @Expose @SerializedName("lat") internal val lat: String? = null,
    @Expose @SerializedName("lon") internal val lon: String? = null
)
