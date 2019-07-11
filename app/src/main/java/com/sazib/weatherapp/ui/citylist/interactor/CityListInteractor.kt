package com.sazib.weatherapp.ui.citylist.interactor

import com.sazib.weatherapp.data.network.ApiHelper
import com.sazib.weatherapp.data.network.request.CityListRequest
import com.sazib.weatherapp.data.network.response.CityListResponse
import com.sazib.weatherapp.data.preferences.PreferenceHelper
import com.sazib.weatherapp.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class CityListInteractor @Inject constructor(
  preferenceHelper: PreferenceHelper,
  apiHelper: ApiHelper
) : BaseInteractor(preferenceHelper, apiHelper), CityListMVPInteractor {

  override fun getAppid(): String  = preferenceHelper.getAppId()

  override fun cityListApiCall(request: CityListRequest): Observable<CityListResponse> =
    apiHelper.cityListApiCall(request)

}