package com.sazib.weatherapp.ui.base.view

import android.content.DialogInterface
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar

interface MVPView {

  fun showProgress()

  fun hideProgress()

  fun setupToolbar(
    toolbar: Toolbar,
    title: String?
  )

  fun setupToolbarBack(
    toolbar: Toolbar,
    title: String?
  )

  fun setupToolbarBack(toolbar: Toolbar)

  fun onError(@StringRes resId: Int)

  fun onError(message: String?)

  fun showMessage(message: String?)

  fun showAlert(
    title: String,
    message: String,
    okListener: DialogInterface.OnClickListener,
    noListener: DialogInterface.OnClickListener
  )

  fun showAlert(
    @StringRes title: Int, message: String,
    okListener: DialogInterface.OnClickListener
  )

  fun showAlert(
    title: String,
    message: String,
    okListener: DialogInterface.OnClickListener
  )

  fun showAlert(
    message: String,
    okListener: DialogInterface.OnClickListener
  )

  fun showAlert(@StringRes message: Int, okListener: DialogInterface.OnClickListener)

  fun showAlert(@StringRes title: Int, message: String)

  fun showAlert(@StringRes message: Int)

  fun showAlert(message: String)

  fun showMessage(@StringRes resId: Int)

  fun finishAction(): DialogInterface.OnClickListener

  fun isNetworkConnected(): Boolean

  fun hideKeyboard()

  fun showKeyboard()

  fun getResString(resString: Int): String

  fun finishIt()

  fun error()

  fun error(message: String?)

}