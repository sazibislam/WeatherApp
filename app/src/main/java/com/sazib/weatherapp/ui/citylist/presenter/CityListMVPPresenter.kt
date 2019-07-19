package com.sazib.weatherapp.ui.citylist.presenter

import com.sazib.weatherapp.ui.base.presenter.MVPPresenter
import com.sazib.weatherapp.ui.citylist.interactor.CityListMVPInteractor
import com.sazib.weatherapp.ui.citylist.view.CityListMVPView

interface CityListMVPPresenter<V : CityListMVPView, I : CityListMVPInteractor> :
    MVPPresenter<V, I> {

  fun getCityListData()

  fun setLocation(
    latitude: String,
    longitude: String
  )

  fun setCity(city: String)
}