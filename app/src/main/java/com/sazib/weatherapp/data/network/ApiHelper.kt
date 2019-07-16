package com.sazib.weatherapp.data.network

import com.sazib.weatherapp.data.network.request.CityListRequest
import com.sazib.weatherapp.data.network.response.CityListResponse
import com.sazib.weatherapp.data.network.response.WeatherDataResponse
import io.reactivex.Observable

interface ApiHelper {
  fun getApiHeader(): ApiHeader

  fun cityListApiCall(request: CityListRequest): Observable<WeatherDataResponse>

}