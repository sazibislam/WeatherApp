package com.sazib.weatherapp.ui.splash

import com.sazib.weatherapp.ui.splash.interactor.SplashInteractor
import com.sazib.weatherapp.ui.splash.interactor.SplashMVPInteractor
import com.sazib.weatherapp.ui.splash.presentation.SplashMVPPresenter
import com.sazib.weatherapp.ui.splash.presentation.SplashPresenter
import com.sazib.weatherapp.ui.splash.view.SplashMVPView
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

  @Provides internal fun provideSplashInteractor(
    interactor: SplashInteractor
  ): SplashMVPInteractor = interactor

  @Provides internal fun provideSplashPresenter(
    presenter: SplashPresenter<SplashMVPView, SplashMVPInteractor>
  ): SplashMVPPresenter<SplashMVPView, SplashMVPInteractor> = presenter

}