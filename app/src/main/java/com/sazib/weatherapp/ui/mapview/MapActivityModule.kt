package com.sazib.weatherapp.ui.mapview

import com.sazib.weatherapp.ui.mapview.interactor.MapInteractor
import com.sazib.weatherapp.ui.mapview.interactor.MapMVPInteractor
import com.sazib.weatherapp.ui.mapview.presenter.MapMVPPresenter
import com.sazib.weatherapp.ui.mapview.presenter.MapPresenter
import com.sazib.weatherapp.ui.mapview.view.MapMVPView
import dagger.Module
import dagger.Provides

@Module
class MapActivityModule {

  @Provides internal fun provideMapInteractor(
    interactor: MapInteractor
  ): MapMVPInteractor = interactor

  @Provides internal fun provideMapPresenter(
    presenter: MapPresenter<MapMVPView, MapMVPInteractor>
  ): MapMVPPresenter<MapMVPView, MapMVPInteractor> =
    presenter
}