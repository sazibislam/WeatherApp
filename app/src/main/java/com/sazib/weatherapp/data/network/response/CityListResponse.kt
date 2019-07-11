package com.sazib.weatherapp.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityListResponse internal constructor(
  @SerializedName("message") @Expose var message: String? = null,
  @SerializedName("cod") @Expose var cod: String? = null,
  @SerializedName("count") @Expose var count: Int? = null,
  @SerializedName("list") @Expose var cityList: List<CityList>? = null
) {

  data class CityList internal constructor(
    @SerializedName("id") @Expose val id: Int? = null,
    @SerializedName("name") @Expose val name: String? = null,
    @SerializedName("coord") @Expose var coord: List<coordList>? = null,
    @SerializedName("main") @Expose var main: List<mainList>? = null,
    @SerializedName("dt") @Expose val dt: Int? = null,
    @SerializedName("wind") @Expose var wind: List<windList>? = null,
    @SerializedName("sys") @Expose var sys: List<sysList>? = null,
    @SerializedName("rain") @Expose val rain: String? = null,
    @SerializedName("snow") @Expose val snow: String? = null,
    @SerializedName("clouds") @Expose var clouds: List<cloudsList>? = null,
    @SerializedName("weather") @Expose var weather: List<weatherList>? = null
  ) {
    data class weatherList internal constructor(
      @SerializedName("id") @Expose val id: Int? = null,
      @SerializedName("main") @Expose val main: String? = null,
      @SerializedName("description") @Expose val description: String? = null,
      @SerializedName("icon") @Expose val icon: String? = null
    )
  }
  data class coordList internal constructor(
    @SerializedName("lat") @Expose val lat: Float? = null,
    @SerializedName("lon") @Expose val lon: Float? = null
  )

  data class mainList internal constructor(
    @SerializedName("temp") @Expose val temp: Float? = null,
    @SerializedName("pressure") @Expose val pressure: Float? = null,
    @SerializedName("humidity") @Expose val humidity: Float? = null,
    @SerializedName("temp_min") @Expose val temp_min: Float? = null,
    @SerializedName("temp_max") @Expose val temp_max: Float? = null
  )

  data class windList internal constructor(
    @SerializedName("speed") @Expose val speed: Float? = null,
    @SerializedName("deg") @Expose val deg: Float? = null
  )

  data class sysList internal constructor(
    @SerializedName("country") @Expose val country: String? = null
  )

  data class cloudsList internal constructor(
    @SerializedName("all") @Expose val all: Int? = null
  )
}





