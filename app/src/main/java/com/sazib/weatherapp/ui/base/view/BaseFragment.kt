package com.sazib.weatherapp.ui.base.view

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.sazib.weatherapp.utils.CommonUtil

abstract class BaseFragment : Fragment() {

  private var parentActivity: BaseActivity? = null
  private var dialog: Dialog? = null

  override fun onAttach(context: Context) {
    super.onAttach(context)
    if (context is BaseActivity) {
      val activity = context as BaseActivity?
      this.parentActivity = activity
      activity?.onAttachFragment()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setUp(view)
  }

  fun setupToolbar(
    toolbar: Toolbar,
    title: String?
  ) {
    parentActivity?.setupToolbar(toolbar, title)
  }

  fun setupToolbarBack(
    toolbar: Toolbar,
    title: String?
  ) {
    parentActivity?.setupToolbarBack(toolbar, title)
  }

  fun onError(resId: Int) {
    parentActivity?.onError(resId)
  }

  fun onError(message: String?) {
    parentActivity?.onError(message)
  }

  fun showMessage(message: String?) {
    parentActivity?.showMessage(message)
  }

  fun showAlert(
    title: String,
    message: String,
    okListener: OnClickListener,
    noListener: OnClickListener
  ) {
    parentActivity?.showAlert(
        title, message, okListener, noListener
    )
  }

  fun showAlert(
    title: Int,
    message: String,
    okListener: OnClickListener
  ) {
    parentActivity?.showAlert(title, message, okListener)
  }

  fun showAlert(
    title: String,
    message: String,
    okListener: OnClickListener
  ) {
    parentActivity?.showAlert(title, message, okListener)
  }

  fun showAlert(
    message: String,
    okListener: OnClickListener
  ) {
    parentActivity?.showAlert(message, okListener)
  }

  fun showAlert(
    message: Int,
    okListener: OnClickListener
  ) {
    parentActivity?.showAlert(message, okListener)
  }

  fun showAlert(
    title: Int,
    message: String
  ) {
    parentActivity?.showAlert(title, message)
  }

  fun showAlert(message: Int) {
    parentActivity?.showAlert(message)
  }

  fun showAlert(message: String) {
    parentActivity?.showAlert(message)
  }

  fun showMessage(resId: Int) {
    parentActivity?.showAlert(resId)
  }

  fun finishAction(): OnClickListener {
    return parentActivity?.FinishAction()!!
  }

  fun isNetworkConnected(): Boolean {
    return parentActivity?.isNetworkConnected()!!
  }

  fun hideKeyboard() {
    parentActivity?.hideKeyboard()
  }

  fun getResString(resString: Int): String {
    return parentActivity?.getResString(resString)!!
  }

  fun openActivityOnTokenExpire() {
    parentActivity?.openActivityOnTokenExpire()
  }

  fun finishIt() {
    parentActivity?.finishIt()
  }

  fun hideProgress() {
    dialog?.let { dialog -> if (dialog.isShowing) dialog.cancel() }
  }

  fun showProgress() {
    hideProgress()
    dialog = this.context?.let { CommonUtil.showLoadingDialog(it) }
  }

  fun getBaseActivity() = parentActivity

  abstract fun setUp(view: View)
}