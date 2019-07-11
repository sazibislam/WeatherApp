package com.sazib.weatherapp.di.builder

import com.sazib.weatherapp.ui.citylist.CityListActivityModule
import com.sazib.weatherapp.ui.citylist.view.CityListActivity
import com.sazib.weatherapp.ui.splash.SplashActivityModule
import com.sazib.weatherapp.ui.splash.view.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = [(SplashActivityModule::class)])
  abstract fun bindSplashActivity(): SplashActivity

  @ContributesAndroidInjector(modules = [(CityListActivityModule::class)])
  abstract fun bindCityListActivity(): CityListActivity

}