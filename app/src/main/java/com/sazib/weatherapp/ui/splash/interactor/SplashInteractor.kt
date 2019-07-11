package com.sazib.weatherapp.ui.splash.interactor

import com.sazib.weatherapp.data.network.ApiHelper
import com.sazib.weatherapp.data.preferences.PreferenceHelper
import com.sazib.weatherapp.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class SplashInteractor @Inject constructor(
  preferenceHelper: PreferenceHelper,
  apiHelper: ApiHelper
) : BaseInteractor(preferenceHelper, apiHelper), SplashMVPInteractor