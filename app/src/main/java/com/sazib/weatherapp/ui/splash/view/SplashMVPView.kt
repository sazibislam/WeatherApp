package com.sazib.weatherapp.ui.splash.view

import com.sazib.weatherapp.ui.base.view.MVPView

interface SplashMVPView : MVPView {

  fun start(isLoggedIn: Boolean)
}