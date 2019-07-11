package com.sazib.weatherapp.ui.mapview.interactor

import com.sazib.weatherapp.data.network.ApiHelper
import com.sazib.weatherapp.data.preferences.PreferenceHelper
import com.sazib.weatherapp.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class MapInteractor @Inject constructor(
  preferenceHelper: PreferenceHelper,
  apiHelper: ApiHelper
) : BaseInteractor(preferenceHelper, apiHelper), MapMVPInteractor