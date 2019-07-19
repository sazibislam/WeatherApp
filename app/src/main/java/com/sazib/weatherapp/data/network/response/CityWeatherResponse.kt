package com.sazib.weatherapp.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityWeatherResponse internal constructor(
  @SerializedName("coord") @Expose var coord: Coord? = null,
  @SerializedName("weather") @Expose var weather: List<Weather>? = null,
  @SerializedName("base") @Expose var base: String? = null,
  @SerializedName("main") @Expose var main: Main? = null,
  @SerializedName("wind") @Expose var wind: Wind? = null,
  @SerializedName("clouds") @Expose var clouds: Clouds? = null,
  @SerializedName("dt") @Expose var dt: Int? = null,
  @SerializedName("sys") @Expose var sys: Sys? = null,
  @SerializedName("id") @Expose var id: Int? = null,
  @SerializedName("name") @Expose var name: String? = null,
  @SerializedName("cod") @Expose var cod: String? = null
) {
  data class Coord(
    @SerializedName("lat") @Expose var lat: Double? = null,
    @SerializedName("lon") @Expose var lon: Double? = null
  )

  data class Weather(
    @SerializedName("id") @Expose var id: Int? = null,
    @SerializedName("main") @Expose var main: String? = null,
    @SerializedName("description") @Expose var description: String? = null,
    @SerializedName("icon") @Expose var icon: String? = null
  )

  data class Main(
    @SerializedName("temp") @Expose var temp: Double? = null,
    @SerializedName("pressure") @Expose var pressure: Float? = null,
    @SerializedName("humidity") @Expose var humidity: Int? = null,
    @SerializedName("temp_min") @Expose var tempMin: Double? = null,
    @SerializedName("temp_max") @Expose var tempMax: Double? = null,
    @SerializedName("sea_level") @Expose var sea_level: Double? = null,
    @SerializedName("grnd_level") @Expose var grnd_level: Double? = null
  )

  data class Wind(
    @SerializedName("speed") @Expose var speed: Float? = null,
    @SerializedName("deg") @Expose var deg: Int? = null
  )

  data class Clouds(
    @SerializedName("all") @Expose var all: Int? = null
  )

  data class Sys(
    @SerializedName("country") @Expose var country: String? = null,
    @SerializedName("message") @Expose var message: String? = null,
    @SerializedName("sunrise") @Expose var sunrise: String? = null,
    @SerializedName("sunset") @Expose var sunset: String? = null
  )
}



