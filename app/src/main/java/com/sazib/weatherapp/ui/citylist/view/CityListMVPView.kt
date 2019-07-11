package com.sazib.weatherapp.ui.citylist.view

import com.sazib.weatherapp.data.network.response.CityListResponse
import com.sazib.weatherapp.ui.base.view.MVPView

interface CityListMVPView : MVPView {

  fun initView()

  fun setAdapterData(data_: List<CityListResponse.CityList>)

  //fun setAdapterPage(page_: Int)

  fun getIsLoading(): Boolean

  fun setIsLoading(isLoading: Boolean)
}