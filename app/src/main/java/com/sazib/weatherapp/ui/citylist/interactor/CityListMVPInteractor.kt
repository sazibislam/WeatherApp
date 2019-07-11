package com.sazib.weatherapp.ui.citylist.interactor

import com.sazib.weatherapp.data.network.request.CityListRequest
import com.sazib.weatherapp.data.network.response.CityListResponse
import com.sazib.weatherapp.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface CityListMVPInteractor : MVPInteractor {

  fun cityListApiCall(
    request: CityListRequest
  ): Observable<CityListResponse>

  fun getAppid(): String

}