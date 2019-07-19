package com.sazib.weatherapp.di.module

import android.app.Application
import android.content.Context
import com.sazib.weatherapp.BuildConfig
import com.sazib.weatherapp.data.network.ApiHeader
import com.sazib.weatherapp.data.network.ApiHelper
import com.sazib.weatherapp.data.network.AppApiHelper
import com.sazib.weatherapp.data.preferences.AppPreferenceHelper
import com.sazib.weatherapp.data.preferences.PreferenceHelper
import com.sazib.weatherapp.di.AppKey
import com.sazib.weatherapp.di.PackageName
import com.sazib.weatherapp.di.PreferenceInfo
import com.sazib.weatherapp.di.VersionName
import com.sazib.weatherapp.utils.AppConstants
import com.sazib.weatherapp.utils.CertSHA1
import com.sazib.weatherapp.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {

  @Provides @Singleton internal fun provideContext(application: Application): Context = application

  /* @Provides @Singleton internal fun provideAppDatabase(context: Context): AppDatabase =
     Room.databaseBuilder(
         context.applicationContext, AppDatabase::class.java, AppConstants.DB_NAME
     ).allowMainThreadQueries().build()*/

  @Provides @AppKey internal fun provideAppKey(context: Context): String =
    CertSHA1.getCertificateSHA1(context.applicationContext)

  @Provides @PackageName internal fun providePackageName(): String = BuildConfig.APPLICATION_ID

  @Provides @VersionName internal fun provideVersionName(): String = BuildConfig.VERSION_NAME

  @Provides @PreferenceInfo internal fun providePrefFileName(): String = AppConstants.PREF_NAME

  @Provides @Singleton internal fun providePrefHelper(appPreferenceHelper: AppPreferenceHelper):
      PreferenceHelper = appPreferenceHelper

  @Provides @Singleton internal fun provideProtectedApiHeader(
    @AppKey app_key: String,
    @PackageName packageName: String,
    @VersionName versionName: String
  ): ApiHeader.AuthApiHeader = ApiHeader.AuthApiHeader(
      _app_key = app_key,
      _package = packageName,
      _version = versionName
  )

  @Provides @Singleton internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper =
    appApiHelper

/*  @Provides @Singleton
  internal fun provideHealthArticleRepoHelper(appDatabase: AppDatabase): HealthArticleRepo =
    HealthArticleRepository(appDatabase.healthArticleDao())

  @Provides @Singleton
  internal fun provideHealthServiceRepoHelper(appDatabase: AppDatabase): HealthServiceRepo =
    HealthServiceRepository(appDatabase.healthServiceDao())*/

  @Provides internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

  @Provides internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

}