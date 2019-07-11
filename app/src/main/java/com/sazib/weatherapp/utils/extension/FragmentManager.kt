package com.sazib.weatherapp.utils.extension

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sazib.weatherapp.R

internal fun androidx.fragment.app.FragmentManager.removeFragment(
  tag: String,
  slideIn: Int = R.anim.slide_left,
  slideOut: Int = R.anim.slide_right
) {
  this.findFragmentByTag(tag)
      ?.let {
        this.beginTransaction()
            .disallowAddToBackStack()
            .setCustomAnimations(slideIn, slideOut)
            .remove(it)
            .commitNow()
      }
}

internal fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
  val fragmentTransaction = beginTransaction()
  fragmentTransaction.func()
  fragmentTransaction.commit()
}

internal fun androidx.fragment.app.FragmentManager.addFragment(
  containerViewId: Int,
  fragment: androidx.fragment.app.Fragment,
  tag: String,
  slideIn: Int = R.anim.slide_left,
  slideOut: Int = R.anim.slide_right
) {
  this.beginTransaction()
      .disallowAddToBackStack()
      .setCustomAnimations(slideIn, slideOut)
      .add(containerViewId, fragment, tag)
      .commit()
}

