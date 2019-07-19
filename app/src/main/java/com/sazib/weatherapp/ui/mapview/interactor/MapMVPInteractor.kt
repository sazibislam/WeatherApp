package com.sazib.weatherapp.ui.mapview.interactor

import com.sazib.weatherapp.ui.base.interactor.MVPInteractor

interface MapMVPInteractor : MVPInteractor {

  fun getLat(): Double?

  fun getLon(): Double?

  fun getCityName(): String?
}