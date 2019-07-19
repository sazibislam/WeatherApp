package com.sazib.weatherapp.ui.mapview.interactor

import com.sazib.weatherapp.data.network.request.CityWeatherRequest
import com.sazib.weatherapp.data.network.response.CityWeatherResponse
import com.sazib.weatherapp.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface MapMVPInteractor : MVPInteractor {

  fun cityListApiCall(
    request: CityWeatherRequest
  ): Observable<CityWeatherResponse>

  fun getAppid(): String?

  fun getLat(): Double?

  fun getLon(): Double?

  fun getCityName(): String?
}