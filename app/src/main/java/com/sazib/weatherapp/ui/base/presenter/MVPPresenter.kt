package com.sazib.weatherapp.ui.base.presenter

import com.androidnetworking.error.ANError
import com.sazib.weatherapp.ui.base.interactor.MVPInteractor
import com.sazib.weatherapp.ui.base.view.MVPView

interface MVPPresenter<V : MVPView, I : MVPInteractor> {

  fun onAttach(view: V?)

  fun onDetach()

  fun getView(): V?

  fun handleApiError(error: ANError?)

}