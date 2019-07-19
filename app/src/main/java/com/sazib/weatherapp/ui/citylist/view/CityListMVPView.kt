package com.sazib.weatherapp.ui.citylist.view

import com.sazib.weatherapp.data.network.response.WeatherDataResponse
import com.sazib.weatherapp.ui.base.view.MVPView

interface CityListMVPView : MVPView {

  fun initView()

  fun setAdapterData(data_: List<WeatherDataResponse.ListData>)

  //fun setAdapterPage(page_: Int)

  fun getIsLoading(): Boolean

  fun setIsLoading(isLoading: Boolean)

  fun checkGps()

  fun getLocation()

  fun setLocation(
    latitude: String,
    longitude: String
  )
}