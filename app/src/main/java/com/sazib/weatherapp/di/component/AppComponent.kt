package com.sazib.weatherapp.di.component

import android.app.Application
import com.sazib.weatherapp.di.builder.ActivityBuilder
import com.sazib.weatherapp.di.module.AppModule
import com.sazib.weatherapp.service.IVApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)]
)
interface AppComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance fun application(application: Application): Builder
    fun build(): AppComponent
  }

  fun inject(app: IVApp)
}