package com.sazib.weatherapp.ui.base.presenter

import com.androidnetworking.common.ANConstants
import com.androidnetworking.error.ANError
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.sazib.weatherapp.R
import com.sazib.weatherapp.data.network.response.ApiError
import com.sazib.weatherapp.ui.base.interactor.MVPInteractor
import com.sazib.weatherapp.ui.base.view.MVPView
import com.sazib.weatherapp.utils.AppConstants
import com.sazib.weatherapp.utils.SchedulerProvider
import com.sazib.weatherapp.utils.logger.AppLogger
import io.reactivex.disposables.CompositeDisposable
import javax.net.ssl.HttpsURLConnection

abstract class BasePresenter<V : MVPView, I : MVPInteractor> internal constructor(
  protected var interactor: I?,
  protected val schedulerProvider: SchedulerProvider,
  protected val compositeDisposable: CompositeDisposable
) : MVPPresenter<V, I> {

  private var view: V? = null
  private val isViewAttached: Boolean get() = view != null

  override fun onAttach(view: V?) {
    this.view = view
  }

  override fun getView(): V? = view

  override fun onDetach() {
    compositeDisposable.dispose()
    view = null
    interactor = null
  }

  override fun handleApiError(error: ANError?) {

    error?.let { error_ ->
      val details = "ANError:\n" +
          "errorCode : ${error_.errorCode}\n" +
          "errorBody : ${error_.errorBody}\n" +
          "errorDetail : ${error_.errorDetail}\n" +
          "message : ${error_.message}"
//      Crashlytics.log(Log.ERROR, "handleApiError", details)
      AppLogger.e(details)
    }

    view?.hideProgress()

    if (error == null) {
      view?.showMessage(R.string.api_default_error)
      return
    }

    if (error.errorCode == AppConstants.API_STATUS_CODE_LOCAL_ERROR && error.errorDetail == ANConstants.CONNECTION_ERROR) {
      view?.showMessage(R.string.connection_error)
      return
    }

    if (error.errorCode == AppConstants.API_STATUS_CODE_LOCAL_ERROR && error.errorDetail == ANConstants.REQUEST_CANCELLED_ERROR) {
      view?.showMessage(R.string.api_retry_error)
      return
    }

    if (error.errorBody == null) {
      view?.showMessage(R.string.api_default_error)
      return
    }

    val builder = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
    val gson = builder.create()

    try {
      val apiError = gson.fromJson(error.errorBody, ApiError::class.java)
      apiError.errorCode = error.errorCode

      if (apiError?.message == null) {
        view?.showMessage(R.string.api_default_error)
      }

      when (apiError.errorCode) {

        HttpsURLConnection.HTTP_FORBIDDEN -> view?.showMessage(apiError.message)

        HttpsURLConnection.HTTP_INTERNAL_ERROR, HttpsURLConnection.HTTP_NOT_FOUND ->
          view?.showMessage(apiError.message)

        else -> view?.showMessage(apiError.message)
      }
    } catch (e: JsonSyntaxException) {
      AppLogger.e("handleApiError: $e")
      view?.showMessage(R.string.api_default_error)
    } catch (e: NullPointerException) {
      AppLogger.e("handleApiError $e")
      view?.showMessage(R.string.api_default_error)
    }
  }
}