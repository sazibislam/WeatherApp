package com.sazib.weatherapp.ui.splash.presentation

import com.sazib.weatherapp.ui.base.presenter.MVPPresenter
import com.sazib.weatherapp.ui.splash.interactor.SplashMVPInteractor
import com.sazib.weatherapp.ui.splash.view.SplashMVPView

interface SplashMVPPresenter<V : SplashMVPView, I : SplashMVPInteractor> : MVPPresenter<V, I>