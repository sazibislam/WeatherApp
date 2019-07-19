package com.sazib.weatherapp.ui.mapview.presenter

import com.sazib.weatherapp.ui.base.presenter.MVPPresenter
import com.sazib.weatherapp.ui.mapview.interactor.MapMVPInteractor
import com.sazib.weatherapp.ui.mapview.view.MapMVPView

interface MapMVPPresenter<V : MapMVPView, I : MapMVPInteractor> : MVPPresenter<V, I> {

  fun getMapData()

  fun getLat(): Double?

  fun getLon(): Double?

  fun getCityName(): String
}