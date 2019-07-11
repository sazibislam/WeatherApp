package com.sazib.weatherapp.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

object ScreenUtils {

  fun getScreenWidth(context: Context): Int {
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    windowManager.let {
      val dm = DisplayMetrics()
      it.defaultDisplay.getMetrics(dm)
      return dm.widthPixels
    }
  }

  fun getScreenHeight(context: Context): Int {
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    windowManager.let {
      val dm = DisplayMetrics()
      it.defaultDisplay.getMetrics(dm)
      return dm.heightPixels
    }
  }
}
