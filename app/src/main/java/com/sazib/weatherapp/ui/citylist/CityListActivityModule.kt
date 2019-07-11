package com.sazib.weatherapp.ui.citylist

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sazib.weatherapp.ui.citylist.interactor.CityListInteractor
import com.sazib.weatherapp.ui.citylist.interactor.CityListMVPInteractor
import com.sazib.weatherapp.ui.citylist.presenter.CityListMVPPresenter
import com.sazib.weatherapp.ui.citylist.presenter.CityListPresenter
import com.sazib.weatherapp.ui.citylist.view.CityListActivity
import com.sazib.weatherapp.ui.citylist.view.CityListMVPView
import com.sazib.weatherapp.ui.citylist.view.adapter.CityListAdapter
import dagger.Module
import dagger.Provides

@Module
class CityListActivityModule {

  @Provides internal fun provideCityListInteractor(
    interactor: CityListInteractor
  ): CityListMVPInteractor = interactor

  @Provides internal fun provideCityListPresenter(
    presenter: CityListPresenter<CityListMVPView, CityListMVPInteractor>
  ): CityListMVPPresenter<CityListMVPView, CityListMVPInteractor> =
    presenter

  @Provides internal fun provideMedicinesLinearLayoutManager(
    activity: CityListActivity
  ): LinearLayoutManager = LinearLayoutManager(
      activity.applicationContext, RecyclerView.VERTICAL, false
  )

  @Provides internal fun provideMedicinesAdapter(): CityListAdapter = CityListAdapter()

}