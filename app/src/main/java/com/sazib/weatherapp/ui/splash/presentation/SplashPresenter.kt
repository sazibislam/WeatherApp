package com.sazib.weatherapp.ui.splash.presentation

import com.sazib.weatherapp.ui.base.presenter.BasePresenter
import com.sazib.weatherapp.ui.splash.interactor.SplashMVPInteractor
import com.sazib.weatherapp.ui.splash.view.SplashMVPView
import com.sazib.weatherapp.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenter<V : SplashMVPView, I : SplashMVPInteractor> @Inject internal constructor(
  interactor: I,
  schedulerProvider: SchedulerProvider,
  disposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable
), SplashMVPPresenter<V, I> {

  override fun onAttach(view: V?) {
    super.onAttach(view)
    getView()?.start(true)
  }
}