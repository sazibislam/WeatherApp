package com.sazib.weatherapp.data.network

import com.sazib.weatherapp.BuildConfig

object ApiEndPoint {

  private const val BASE_URL = BuildConfig.BASE_URL

  const val ENDPOINT_CITY_DATA = "$BASE_URL/data/2.5/find"

  const val ENDPOINT_CITY_WEATHER_DATA = "$BASE_URL/data/2.5/weather"

}