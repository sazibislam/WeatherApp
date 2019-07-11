package com.sazib.weatherapp.utils

import android.app.Activity
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.sazib.weatherapp.R

class IVToast {
  private var mToast: Toast? = null

  fun show(
    activity: Activity,
    message: String
  ) {

    sInstance?.mToast?.cancel()

    val inflater = activity.layoutInflater

    val layout = inflater.inflate(
        R.layout.toast_layout,
        activity.findViewById<View>(R.id.custom_toast_layout_id) as ViewGroup?
    )

    val text = layout.findViewById<TextView>(R.id.text)
    text.text = message.toSpanned()

    sInstance?.mToast = Toast(activity)
    sInstance?.mToast?.duration = Toast.LENGTH_SHORT
    sInstance?.mToast?.view = layout
    sInstance?.mToast?.show()
  }

  private fun String.toSpanned(): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
      @Suppress("DEPRECATION") return Html.fromHtml(this)
    }
  }

  companion object {

    private var sInstance: IVToast? = null

    val instance: IVToast
      @Synchronized get() {
        if (sInstance == null) sInstance = IVToast()
        return sInstance as IVToast
      }
  }
}
