package com.sazib.weatherapp.ui.base.interactor

import com.sazib.weatherapp.data.network.ApiHelper
import com.sazib.weatherapp.data.preferences.PreferenceHelper

open class BaseInteractor() : MVPInteractor {

  protected lateinit var preferenceHelper: PreferenceHelper
  protected lateinit var apiHelper: ApiHelper

  constructor(
    preferenceHelper: PreferenceHelper,
    apiHelper: ApiHelper
  ) : this() {
    this.preferenceHelper = preferenceHelper
    this.apiHelper = apiHelper
  }

  override fun getUserId(): String = preferenceHelper.getUserId()

  override fun getToken(): String = preferenceHelper.getAccessToken()

}