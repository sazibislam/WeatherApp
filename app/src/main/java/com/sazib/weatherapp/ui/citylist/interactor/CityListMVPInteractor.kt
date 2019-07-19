package com.sazib.weatherapp.ui.citylist.interactor

import com.sazib.weatherapp.data.network.request.CityListRequest
import com.sazib.weatherapp.data.network.response.WeatherDataResponse
import com.sazib.weatherapp.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface CityListMVPInteractor : MVPInteractor {

  fun cityListApiCall(
    request: CityListRequest
  ): Observable<WeatherDataResponse>

  fun getAppid(): String?

  fun setLat(latitude: String)

  fun setLon(longitude: String)

  fun getLat(): String?

  fun getLon(): String?

  fun setCity(city: String)
}