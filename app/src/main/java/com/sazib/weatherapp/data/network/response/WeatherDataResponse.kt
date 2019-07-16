package com.sazib.weatherapp.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherDataResponse(
  @SerializedName("message") @Expose var message: String? = null,
  @SerializedName("cod") @Expose var cod: String? = null,
  @SerializedName("count") @Expose var count: Int? = null,
  @SerializedName("list") @Expose var list: List<ListData>? = null
) {

  data class Weather(
    @SerializedName("id") @Expose var id: Int? = null,
    @SerializedName("main") @Expose var main: String? = null,
    @SerializedName("description") @Expose var description: String? = null,
    @SerializedName("icon") @Expose var icon: String? = null
  )

  data class Wind(
    @SerializedName("speed") @Expose var speed: Double? = null,
    @SerializedName("deg") @Expose var deg: Double? = null
  )

  data class ListData(
    @SerializedName("id") @Expose var id: Int? = null,
    @SerializedName("name") @Expose var name: String? = null,
    @SerializedName("coord") @Expose var coord: Coord? = null,
    @SerializedName("main") @Expose var main: Main? = null,
    @SerializedName("dt") @Expose var dt: Int? = null,
    @SerializedName("wind") @Expose var wind: Wind? = null,
    @SerializedName("sys") @Expose var sys: Sys? = null,
    @SerializedName("rain") @Expose var rain: Any? = null,
    @SerializedName("snow") @Expose var snow: Any? = null,
    @SerializedName("clouds") @Expose var clouds: Clouds? = null,
    @SerializedName("weather") @Expose var weather: List<Weather>? = null
  ) {

    data class Coord(
      @SerializedName("lat") @Expose var lat: Double? = null,
      @SerializedName("lon") @Expose var lon: Double? = null
    )

    data class Clouds(
      @SerializedName("all") @Expose var all: Int? = null
    )

    data class Wind(
      @SerializedName("speed") @Expose var speed: Double? = null,
      @SerializedName("deg") @Expose var deg: Int? = null
    )

    data class Weather(
      @SerializedName("id") @Expose var id: Int? = null,
      @SerializedName("main") @Expose var main: String? = null,
      @SerializedName("description") @Expose var description: String? = null,
      @SerializedName("icon") @Expose var icon: String? = null
    )

    data class Sys(
      @SerializedName("country") @Expose var country: String? = null
    )

    data class Main(
      @SerializedName("temp") @Expose var temp: Double? = null,
      @SerializedName("pressure") @Expose var pressure: Int? = null,
      @SerializedName("humidity") @Expose var humidity: Int? = null,
      @SerializedName("temp_min") @Expose var tempMin: Double? = null,
      @SerializedName("temp_max") @Expose var tempMax: Double? = null
    )
  }
}