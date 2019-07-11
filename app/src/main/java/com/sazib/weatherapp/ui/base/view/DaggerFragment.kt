package com.sazib.weatherapp.ui.base.view

import android.content.Context
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class DaggerFragment : Fragment(), MVPView {

  private var parentActivity: DaggerActivity? = null
  protected var savedInstanceState: Bundle? = null

  override fun onAttach(context: Context) {
    super.onAttach(context)
    if (context is DaggerActivity) {
      val activity = context as DaggerActivity?
      this.parentActivity = activity
      activity?.onFragmentAttached()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    performDependencyInjection()
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    this.savedInstanceState = savedInstanceState
    setUp(view)
  }

  override fun hideProgress() {
    parentActivity?.hideProgress()
  }

  override fun showProgress() {
    parentActivity?.showProgress()
  }

  override fun setupToolbar(
    toolbar: Toolbar,
    title: String?
  ) {
    parentActivity?.setupToolbar(toolbar, title)
  }

  override fun setupToolbarBack(
    toolbar: Toolbar,
    title: String?
  ) {
    parentActivity?.setupToolbarBack(toolbar, title)
  }

  override fun onError(message: String?) {
    parentActivity?.onError(message)
  }

  override fun onError(resId: Int) {
    parentActivity?.onError(resId)
  }

  override fun showMessage(message: String?) {
    parentActivity?.showMessage(message)
  }

  override fun showAlert(
    title: String,
    message: String,
    okListener: OnClickListener,
    noListener: OnClickListener
  ) {
    parentActivity?.showAlert(title, message, okListener, noListener)
  }

  override fun showAlert(
    title: Int,
    message: String,
    okListener: OnClickListener
  ) {
    parentActivity?.showAlert(title, message, okListener)
  }

  override fun showAlert(
    title: String,
    message: String,
    okListener: OnClickListener
  ) {
    parentActivity?.showAlert(title, message, okListener)
  }

  override fun showAlert(
    message: String,
    okListener: OnClickListener
  ) {
    parentActivity?.showAlert(message, okListener)
  }

  override fun showAlert(
    message: Int,
    okListener: OnClickListener
  ) {
    parentActivity?.showAlert(message, okListener)
  }

  override fun showAlert(
    title: Int,
    message: String
  ) {
    parentActivity?.showAlert(title, message)
  }

  override fun showAlert(message: Int) {
    parentActivity?.showAlert(message)
  }

  override fun showAlert(message: String) {
    parentActivity?.showAlert(message)
  }

  override fun showMessage(resId: Int) {
    parentActivity?.showMessage(resId)
  }

  override fun error() {
    parentActivity?.error()
  }

  override fun error(message: String?) {
    parentActivity?.error(message)
  }

  override fun setupToolbarBack(toolbar: Toolbar) {
    parentActivity?.setupToolbarBack(toolbar)
  }

  override fun finishAction(): OnClickListener {
    return parentActivity?.finishAction()!!
  }

  override fun isNetworkConnected(): Boolean {
    return parentActivity?.isNetworkConnected()!!
  }

  override fun hideKeyboard() {
    parentActivity?.hideKeyboard()
  }

  override fun showKeyboard() {
    parentActivity?.showKeyboard()
  }

  override fun getResString(resString: Int): String {
    return parentActivity?.getResString(resString)!!
  }

  override fun finishIt() {
    parentActivity?.finishIt()
  }

  fun getBaseActivity() = parentActivity

  private fun performDependencyInjection() = AndroidSupportInjection.inject(this)

  interface CallBack {
    fun onFragmentAttached()
    fun onFragmentDetached(tag: String)
  }

  abstract fun setUp(view: View)
}