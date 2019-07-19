package com.sazib.weatherapp.ui.splash.view

import android.os.Bundle
import android.os.Handler
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import com.sazib.weatherapp.R
import com.sazib.weatherapp.ui.base.view.DaggerActivity
import com.sazib.weatherapp.ui.citylist.view.CityListActivity
import com.sazib.weatherapp.ui.splash.interactor.SplashMVPInteractor
import com.sazib.weatherapp.ui.splash.presentation.SplashMVPPresenter
import com.sazib.weatherapp.utils.AppConstants
import javax.inject.Inject

class SplashActivity : DaggerActivity(), SplashMVPView {

  @Inject lateinit var presenter: SplashMVPPresenter<SplashMVPView, SplashMVPInteractor>

  private var mHandler: Handler? = null
  private var mRunnable: Runnable? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
    setContentView(R.layout.activity_splash)
    presenter.onAttach(this)
  }

  override fun start(
    isLoggedIn: Boolean
  ) {
    mHandler = Handler()
      mRunnable = RunSplash()
    mHandler?.postDelayed(mRunnable, AppConstants.SPLASH_TIME_OUT)
  }

  override fun onDestroy() {
    mHandler?.removeCallbacks(mRunnable)
    presenter.onDetach()
    super.onDestroy()
  }

    private inner class RunSplash internal constructor() : Runnable {
    override fun run() {
      startActivity(CityListActivity.getStartIntent(applicationContext))
      finishIt()
    }
  }
}
