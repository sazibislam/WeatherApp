package com.sazib.weatherapp.ui.citylist.view

import com.sazib.weatherapp.ui.base.view.MVPView
import com.sazib.weatherapp.ui.citylist.view.model.CityListDataModel

interface CityListMVPView : MVPView {

  fun initView()

  //fun setAdapterData(data_: List<CityListResponse.CityList>)
  fun setAdapterData(data_: List<CityListDataModel>)

  //fun setAdapterPage(page_: Int)

  fun getIsLoading(): Boolean

  fun setIsLoading(isLoading: Boolean)
}