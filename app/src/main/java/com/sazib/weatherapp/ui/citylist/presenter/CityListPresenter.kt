package com.sazib.weatherapp.ui.citylist.presenter

import com.androidnetworking.error.ANError
import com.sazib.weatherapp.R
import com.sazib.weatherapp.data.network.request.CityListRequest
import com.sazib.weatherapp.ui.base.presenter.BasePresenter
import com.sazib.weatherapp.ui.citylist.interactor.CityListMVPInteractor
import com.sazib.weatherapp.ui.citylist.view.CityListMVPView
import com.sazib.weatherapp.utils.SchedulerProvider
import com.sazib.weatherapp.utils.logger.AppLogger
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CityListPresenter<V : CityListMVPView, I : CityListMVPInteractor> @Inject internal constructor(
  interactor: I,
  schedulerProvider: SchedulerProvider,
  disposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable
), CityListMVPPresenter<V, I> {

  override fun onAttach(view: V?) {
    super.onAttach(view)

    getView()?.initView()
    getView()?.checkGps()
    getCityListData()
  }

  override fun getCityListData() {

    val cnt: Int = 50
    val lat: String? = interactor?.getLat() //?: "23.68"
    val lon: String? = interactor?.getLon() //?: "90.35"
    AppLogger.d(lat + lon)

    getView()?.let { view ->
      if (!view.isNetworkConnected()) {
        view.showMessage(R.string.no_internet)
        return
      }
      view.showProgress()
      interactor?.let { interactor_ ->
        compositeDisposable.add(
            interactor_.cityListApiCall(
                CityListRequest(
                    appid = interactor_.getAppid(),
                    cnt = cnt,
                    lat = lat,
                    lon = lon
                )
            ).compose(SchedulerProvider().ioToMainObservableScheduler())
                .subscribe({ response ->
                  view.hideProgress()
                  response?.cod?.let { cod_ ->
                    AppLogger.json(cod_)
                    when (cod_) {
                      "200" -> {
                        response.list?.let { data_ ->
                          AppLogger.d(data_)
                          data_.let { data__ ->
                            view.setAdapterData(data__)
                          }
                        }
                      }
                      else -> {
                        view.checkGps()
                        view.showMessage(response.message)
                        view.finishIt()
                      }
                    }
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

  override fun setLocation(
    latitude: String,
    longitude: String
  ) {
    interactor?.setLat(latitude)
    interactor?.setLon(longitude)
    getCityListData()
  }

  override fun setCity(city: String) {
    interactor?.setCity(city)
  }
}