package com.sazib.weatherapp.utils

import android.app.Activity
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.sazib.weatherapp.R

class IVSnackBar {
  private var mSnackBar: Snackbar? = null

  fun show(
    activity: Activity,
    message: String
  ) {
    if (sInstance?.mSnackBar != null) {
      sInstance?.mSnackBar?.dismiss()
    }

    mSnackBar = Snackbar.make(
        activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG
    )
    val sbView = mSnackBar?.view
    val textView = sbView?.findViewById<TextView>(R.id.snackbar_text)
    textView?.maxLines = 3
    textView?.setTextColor(ContextCompat.getColor(activity, R.color.white))

    sbView?.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorAccent))
    mSnackBar?.setActionTextColor(ContextCompat.getColor(activity, R.color.white))
    mSnackBar?.setAction("Dismiss") { if (mSnackBar?.isShown == true) mSnackBar?.dismiss() }
    mSnackBar?.show()
  }

  companion object {

    private var sInstance: IVSnackBar? = null

    val instance: IVSnackBar
      @Synchronized get() {
        if (sInstance == null) {
          sInstance = IVSnackBar()
        }
        return sInstance as IVSnackBar
      }
  }
}
