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
  }

  override fun getCityListData() {

    getView()?.let { view ->
      if (!view.isNetworkConnected()) {
        view.showMessage(R.string.no_internet)
        return
      }
      view.showProgress()
      interactor?.let { interactor_ ->
        compositeDisposable.add(
            interactor_.cityListApiCall(
                CityListRequest(appid = interactor_.getAppid(), cnt = "50", lat = "23.68", lon = "90.35")
            ).compose(SchedulerProvider().ioToMainObservableScheduler())
                .subscribe({ response ->
                  view.hideProgress()
                  response?.cod?.let { cod_ ->
                    AppLogger.json(cod_)
                    when (cod_) {
                      "200" -> {
                        response.cityList?.let { data_ ->
                          AppLogger.d(data_)
                          data_.let { data__ ->
                            view.setAdapterData(data__)
                          }
                        }
                      }
                      else -> {
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
}