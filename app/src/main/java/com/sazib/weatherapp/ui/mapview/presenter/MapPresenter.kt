package com.sazib.weatherapp.ui.mapview.presenter

import com.sazib.weatherapp.ui.base.presenter.BasePresenter
import com.sazib.weatherapp.ui.mapview.interactor.MapMVPInteractor
import com.sazib.weatherapp.ui.mapview.view.MapMVPView
import com.sazib.weatherapp.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MapPresenter<V : MapMVPView, I : MapMVPInteractor> @Inject internal constructor(
  interactor: I,
  schedulerProvider: SchedulerProvider,
  disposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable
), MapMVPPresenter<V, I> {

  override fun onAttach(view: V?) {
    super.onAttach(view)
    getView()?.initView()

  }

  override fun getMapData() {

  }

  override fun getLat(): Double? = interactor?.getLat()

  override fun getLon(): Double? = interactor?.getLon()

  override fun getCityName(): String = interactor?.getCityName() ?: ""
}