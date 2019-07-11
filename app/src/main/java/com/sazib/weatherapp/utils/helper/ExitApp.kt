package com.sazib.weatherapp.utils.helper

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes

object ExitApp {

  private val WAIT_TIME: Long = 2500
  private var lastClickTime: Long = 0

  fun now(ctx: Activity, @StringRes message: Int) {
    now(ctx, ctx.getString(message), WAIT_TIME)
  }

  fun now(
    ctx: Activity, @StringRes message: Int,
    time: Long
  ) {
    now(ctx, ctx.getString(message), time)
  }

  private fun now(
    ctx: Activity?,
    message: String,
    time: Long
  ) {
    if (ctx != null && !message.isEmpty() && time != 0L) {
      if (lastClickTime + time > System.currentTimeMillis()) {
        ctx.finish()
      } else {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT)
            .show()
        lastClickTime = System.currentTimeMillis()
      }
    }
  }
}
