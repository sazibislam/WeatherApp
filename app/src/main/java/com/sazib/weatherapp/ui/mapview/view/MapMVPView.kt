package com.sazib.weatherapp.ui.mapview.view

import com.sazib.weatherapp.data.network.response.CityWeatherResponse.Main
import com.sazib.weatherapp.data.network.response.CityWeatherResponse.Weather
import com.sazib.weatherapp.data.network.response.CityWeatherResponse.Wind
import com.sazib.weatherapp.ui.base.view.MVPView

interface MapMVPView : MVPView {

  fun initView()

  fun updateInfo(
    weather: List<Weather>?,
    main: Main?,
    wind: Wind?
  )
}