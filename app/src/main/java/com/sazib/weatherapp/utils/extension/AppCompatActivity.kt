package com.sazib.weatherapp.utils.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.addFragment(
  fragment: Fragment,
  frameId: Int
) {
  supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(
  fragment: Fragment,
  frameId: Int
) {
  supportFragmentManager.inTransaction { replace(frameId, fragment) }
}