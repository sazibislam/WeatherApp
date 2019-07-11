package com.sazib.weatherapp.service

import android.app.Activity
import android.app.Service
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.androidnetworking.AndroidNetworking
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.sazib.weatherapp.BuildConfig
import com.sazib.weatherapp.di.component.DaggerAppComponent
import com.sazib.weatherapp.utils.AppConstants.TIMEOUT
import com.sazib.weatherapp.utils.logger.AppLogger
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class IVApp : MultiDexApplication(), HasActivityInjector, HasServiceInjector {

  @Inject internal lateinit var activityDispatchingAndroidInjector:
      DispatchingAndroidInjector<Activity>
  @Inject internal lateinit var serviceDispatchingAndroidInjector:
      DispatchingAndroidInjector<Service>

  override fun activityInjector() = activityDispatchingAndroidInjector

  override fun serviceInjector() = serviceDispatchingAndroidInjector

  companion object {
    private var mInstance: IVApp? = null

    @Synchronized fun getInstance(): IVApp {
      if (mInstance == null) mInstance = IVApp()
      return mInstance as IVApp
    }
  }

  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }

  override fun onCreate() {
    super.onCreate()
    mInstance = this
    AppLogger.init()

    DaggerAppComponent.builder()
        .application(this)
        .build()
        .inject(this)

/*    val calligraphyConfig = Builder()
        .setDefaultFontPath("fonts/Camphor/Camphor-Regular.ttf")
        .setFontAttrId(R.attr.fontPath)
        .build()

    val interceptor = CalligraphyInterceptor(calligraphyConfig)
    val viewPump = ViewPump.builder()
        .addInterceptor(interceptor)
        .build()
    ViewPump.init(viewPump)*/

    Stetho.initializeWithDefaults(this)

    AndroidNetworking.initialize(
        applicationContext, OkHttpClient().newBuilder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addNetworkInterceptor(StethoInterceptor())
        .build()
    )
    if (BuildConfig.DEBUG) {
//      AndroidNetworking.enableLogging()
//      AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)
    }
    AndroidNetworking.setConnectionQualityChangeListener { currentConnectionQuality, currentBandwidth ->
      val message = "onChange: quality : $currentConnectionQuality \nbandwidth : $currentBandwidth"
      AppLogger.d(message)
    }
  }
}