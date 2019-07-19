package com.sazib.weatherapp.ui.mapview.interactor

import com.sazib.weatherapp.data.network.ApiHelper
import com.sazib.weatherapp.data.network.request.CityWeatherRequest
import com.sazib.weatherapp.data.network.response.CityWeatherResponse
import com.sazib.weatherapp.data.preferences.PreferenceHelper
import com.sazib.weatherapp.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class MapInteractor @Inject constructor(
  preferenceHelper: PreferenceHelper,
  apiHelper: ApiHelper
) : BaseInteractor(preferenceHelper, apiHelper), MapMVPInteractor {

  override fun cityListApiCall(request: CityWeatherRequest): Observable<CityWeatherResponse> =
    apiHelper.cityWeatherApiCall(request)

  override fun getAppid(): String? = preferenceHelper.getAppId()

  override fun getLat(): Double? = preferenceHelper.getLatitude()?.toDouble()

  override fun getLon(): Double? = preferenceHelper.getLongitude()?.toDouble()

  override fun getCityName(): String? = preferenceHelper.getCity()
}