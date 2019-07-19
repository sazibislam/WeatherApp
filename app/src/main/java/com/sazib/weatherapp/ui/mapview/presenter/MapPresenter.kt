package com.sazib.weatherapp.ui.mapview.presenter

import com.androidnetworking.error.ANError
import com.sazib.weatherapp.R
import com.sazib.weatherapp.data.network.request.CityWeatherRequest
import com.sazib.weatherapp.ui.base.presenter.BasePresenter
import com.sazib.weatherapp.ui.mapview.interactor.MapMVPInteractor
import com.sazib.weatherapp.ui.mapview.view.MapMVPView
import com.sazib.weatherapp.utils.SchedulerProvider
import com.sazib.weatherapp.utils.logger.AppLogger
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

    getMapData()

  }

  override fun getMapData() {

    val lat = getLat()
    val lon = getLon()

    getView()?.let { view ->
      if (!view.isNetworkConnected()) {
        view.showMessage(R.string.no_internet)
        return
      }
      view.showProgress()
      interactor?.let { interactor_ ->
        compositeDisposable.add(
            interactor_.cityListApiCall(
                CityWeatherRequest(
                    appid = interactor_.getAppid(),
                    lat = lat.toString(),
                    lon = lon.toString()
                )
            ).compose(SchedulerProvider().ioToMainObservableScheduler())
                .subscribe({ response ->
                  view.hideProgress()
                  response?.let { response_ ->
                    AppLogger.d(response)
                    getView()?.updateInfo(response.weather, response.main, response.wind)
                  }
                }, { throwable ->
                  run {
                    val anError = throwable as ANError
                    handleApiError(anError)
                  }
                })
        )
      }
    }
  }

  override fun getLat(): Double? = interactor?.getLat()

  override fun getLon(): Double? = interactor?.getLon()

  override fun getCityName(): String = interactor?.getCityName() ?: ""
}