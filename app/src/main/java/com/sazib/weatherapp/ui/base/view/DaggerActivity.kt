package com.sazib.weatherapp.ui.base.view

import android.annotation.TargetApi
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.text.toSpanned
import com.sazib.weatherapp.R
import com.sazib.weatherapp.ui.base.view.DaggerFragment.CallBack
import com.sazib.weatherapp.utils.CommonUtil
import com.sazib.weatherapp.utils.IVSnackBar
import com.sazib.weatherapp.utils.NetworkUtils
import com.sazib.weatherapp.utils.logger.AppLogger
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper

abstract class DaggerActivity : DaggerAppCompatActivity(), MVPView, CallBack {

  private var dialog: Dialog? = null
  private var toast: Toast? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    performDI()
    super.onCreate(savedInstanceState)
  }

  override fun attachBaseContext(newBase: Context?) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
  }

  override fun hideProgress() {
    dialog?.let { dialog_ -> if (dialog_.isShowing) dialog_.cancel() }
  }

  override fun showProgress() {
    hideProgress()
    dialog = CommonUtil.showLoadingDialog(this)
  }

  override fun setupToolbar(
    toolbar: Toolbar,
    title: String?
  ) {
    setToolbar(toolbar, title, false)
  }

  override fun setupToolbarBack(
    toolbar: Toolbar,
    title: String?
  ) {
    setToolbar(toolbar, title, true)
  }

  override fun setupToolbarBack(toolbar: Toolbar) {
    setToolbar(toolbar, getResString(R.string.app_name), true)
  }

  private fun setToolbar(
    toolbar: Toolbar,
    title: String?,
    isBack: Boolean
  ) {
    setSupportActionBar(toolbar)
    val bar = supportActionBar

    if (isBack) {
      bar?.setDisplayHomeAsUpEnabled(true)
      bar?.setDisplayShowHomeEnabled(true)
      toolbar.setNavigationOnClickListener { onBackPressed() }
    }
//    bar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#f42a41")))
    bar?.title = title ?: getString(R.string.app_name)
  }

  @TargetApi(Build.VERSION_CODES.M)
  fun requestPermissionsSafely(
    permissions: Array<String>,
    requestCode: Int
  ) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      var isGranted = true

      for (permission in permissions) {
        val granted =
          ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
        if (granted) {
          isGranted = false
          break
        }
      }
      if (isGranted) return
      ActivityCompat.requestPermissions(this, permissions, requestCode)
    }
  }

  @TargetApi(Build.VERSION_CODES.M)
  fun hasPermission(permission: String): Boolean {
    return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(
        permission
    ) == PackageManager.PERMISSION_GRANTED
  }

  override fun hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }

  override fun showKeyboard() {
    val view = this.currentFocus
    if (view != null) {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
  }

  override fun finishAction(): DialogInterface.OnClickListener = FinishAction()

  override fun getResString(resString: Int): String = getString(resString)

  override fun onError(message: String?) =
    IVSnackBar.instance.show(this, message ?: "Some Error Occurred!")

  override fun onError(@StringRes resId: Int) = onError(getString(resId))

  override fun showMessage(message: String?) {
    toast?.cancel()

    val group: ViewGroup? = findViewById<View>(R.id.custom_toast_layout_id) as ViewGroup?
    val layout: View = layoutInflater.inflate(R.layout.toast_layout, group)

    val text = layout.findViewById<TextView>(R.id.text)
    text.text = message?.toSpanned() ?: "Some Error Occurred!"

    toast = Toast(this)
    toast?.duration = Toast.LENGTH_SHORT
    toast?.view = layout
    toast?.show()
  }

  override fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

  override fun isNetworkConnected(): Boolean = NetworkUtils.isNetworkConnected(applicationContext)

  override fun finishIt() = finish()

  private fun makeAlert(
    title: String?,
    message: String,
    okListener: DialogInterface.OnClickListener?,
    noListener: DialogInterface.OnClickListener?
  ) {
    try {
      val builder = AlertDialog.Builder(this)

      if (title != null) {
        builder.setTitle(title)
      }
      builder.setCancelable(false)
      builder.setMessage(message.toSpanned())

      if (okListener != null) {
        builder.setPositiveButton(android.R.string.ok, okListener)
      }

      if (noListener != null) {
        builder.setNegativeButton(android.R.string.cancel, noListener)
      }

      val alert = builder.create()
      alert.setCancelable(false)
      alert.show()
    } catch (e: Exception) {
      AppLogger.e("showMessage: %s", e.message)
    }
  }

  override fun showAlert(
    title: Int,
    message: String,
    okListener: DialogInterface.OnClickListener
  ) = makeAlert(getString(title), message, okListener, null)

  override fun showAlert(
    title: String,
    message: String,
    okListener: DialogInterface.OnClickListener
  ) = makeAlert(title, message, okListener, null)

  override fun showAlert(
    title: String,
    message: String,
    okListener: DialogInterface.OnClickListener,
    noListener: DialogInterface.OnClickListener
  ) = makeAlert(title, message, okListener, noListener)

  override fun showAlert(
    title: Int,
    message: String
  ) = makeAlert(getString(title), message, NoAction(), null)

  override fun showAlert(message: String) = makeAlert(null, message, NoAction(), null)

  override fun showAlert(message: Int) =
    makeAlert(null, getString(message), NoAction(), null)

  override fun showAlert(
    message: String,
    okListener: DialogInterface.OnClickListener
  ) = makeAlert(null, message, okListener, NoAction())

  override fun showAlert(
    message: Int,
    okListener: DialogInterface.OnClickListener
  ) = makeAlert(null, getString(message), okListener, null)

  override fun error() {
    showMessage(R.string.general_error)
    finish()
  }

  override fun error(message: String?) {
    message?.let { message_ ->
      showMessage(message_)
    } ?: showMessage(R.string.general_error)
  }

  override fun onFragmentAttached() {

  }

  override fun onFragmentDetached(tag: String) {

  }

  private fun performDI() = AndroidInjection.inject(this)

  internal inner class FinishAction : DialogInterface.OnClickListener {
    override fun onClick(
      dialog: DialogInterface,
      which: Int
    ) {
      finish()
    }
  }

  internal inner class NoAction : DialogInterface.OnClickListener {
    override fun onClick(
      dialog: DialogInterface,
      which: Int
    ) {

    }
  }
}