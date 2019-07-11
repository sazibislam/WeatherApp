package com.sazib.weatherapp.ui.base.view

import android.annotation.TargetApi
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Html
import android.text.Spanned
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.sazib.weatherapp.R
import com.sazib.weatherapp.data.preferences.AppPreferenceHelper
import com.sazib.weatherapp.utils.CommonUtil
import com.sazib.weatherapp.utils.IVSnackBar
import com.sazib.weatherapp.utils.IVToast
import com.sazib.weatherapp.utils.NetworkUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

  private var dialog: Dialog? = null
  private var preferenceHelper: AppPreferenceHelper? = null

  override fun onCreate(
    savedInstanceState: Bundle?,
    persistentState: PersistableBundle?
  ) {
    super.onCreate(savedInstanceState, persistentState)
    preferenceHelper = AppPreferenceHelper(this)
  }

  override fun attachBaseContext(newBase: Context) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
  }

  fun getPrefHelper(): AppPreferenceHelper {
    preferenceHelper?.let { return it }
    return AppPreferenceHelper(this)
  }

  fun hideProgress() {
    dialog?.let { if (it.isShowing) it.cancel() }
  }

  fun showProgress() {
    hideProgress()
    dialog = CommonUtil.showLoadingDialog(this)
  }

  fun setupToolbar(
    toolbar: Toolbar,
    title: String?
  ) {
    setToolbar(toolbar, title, false)
  }

  fun setupToolbarBack(
    toolbar: Toolbar,
    title: String?
  ) {
    setToolbar(toolbar, title, true)
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
    bar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#009bb8")))
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
        if (ActivityCompat.checkSelfPermission(
                this, permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
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

  fun hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }

  fun finishAction(): DialogInterface.OnClickListener {
    return FinishAction()
  }

  fun getResString(resString: Int): String {
    return getString(resString)
  }

  fun openActivityOnTokenExpire() {
    //startActivity(LoginActivity.getStartIntent(this))
    finish()
  }

  fun onError(message: String?) {
    IVSnackBar.instance.show(this, message ?: "Some Error Occurred!")
  }

  fun onError(@StringRes resId: Int) {
    onError(getString(resId))
  }

  fun showMessage(message: String?) {
    IVToast.instance.show(this, message ?: "Some Error Occurred!")
  }

  fun showMessage(@StringRes resId: Int) {
    showMessage(getString(resId))
  }

  fun isNetworkConnected(): Boolean {
    return NetworkUtils.isNetworkConnected(applicationContext)
  }

  fun finishIt() {
    finish()
  }

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
      Timber.e("showMessage: %s", e.message)
    }

  }

  private fun String.toSpanned(): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
      @Suppress("DEPRECATION") return Html.fromHtml(this)
    }
  }

  fun showAlert(
    title: Int,
    message: String,
    okListener: DialogInterface.OnClickListener
  ) {
    makeAlert(getString(title), message, okListener, null)
  }

  fun showAlert(
    title: String,
    message: String,
    okListener: DialogInterface.OnClickListener
  ) {
    makeAlert(title, message, okListener, null)
  }

  fun showAlert(
    title: String,
    message: String,
    okListener: DialogInterface.OnClickListener,
    noListener: DialogInterface.OnClickListener
  ) {
    makeAlert(title, message, okListener, noListener)
  }

  fun showAlert(
    title: Int,
    message: String
  ) {
    makeAlert(getString(title), message, NoAction(), null)
  }

  fun showAlert(message: String) {
    makeAlert(null, message, NoAction(), null)
  }

  fun showAlert(message: Int) {
    makeAlert(null, getString(message), NoAction(), null)
  }

  fun showAlert(
    message: String,
    okListener: DialogInterface.OnClickListener
  ) {
    makeAlert(null, message, okListener, NoAction())
  }

  fun showAlert(
    message: Int,
    okListener: DialogInterface.OnClickListener
  ) {
    makeAlert(null, getString(message), okListener, null)
  }

  fun onAttachFragment() {

  }

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