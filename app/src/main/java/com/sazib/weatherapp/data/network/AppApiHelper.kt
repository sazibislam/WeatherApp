package com.sazib.weatherapp.data.network

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.sazib.weatherapp.data.network.request.CityListRequest
import com.sazib.weatherapp.data.network.response.CityListResponse
import io.reactivex.Observable
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val mApiHeader: ApiHeader) : ApiHelper {

  override fun getApiHeader(): ApiHeader = mApiHeader

  override fun cityListApiCall(request: CityListRequest): Observable<CityListResponse> =
    Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_CITY_DATA)
        .addHeaders(mApiHeader.authApiHeader)
        .addQueryParameter(request)
        .build()
        .getObjectObservable(CityListResponse::class.java)

}