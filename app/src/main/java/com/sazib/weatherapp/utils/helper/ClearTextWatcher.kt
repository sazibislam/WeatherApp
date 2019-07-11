package com.sazib.weatherapp.utils.helper

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import timber.log.Timber

class ClearTextWatcher(private val editText: EditText) : TextWatcher {

  override fun beforeTextChanged(
    s: CharSequence,
    start: Int,
    count: Int,
    after: Int
  ) {

    Timber.d(
        "beforeTextChanged() called with: s = [" + s + "], start = [" + start + "], count = [" + count + "], after = [" + after + "]"
    )
  }

  override fun onTextChanged(
    s: CharSequence,
    start: Int,
    before: Int,
    count: Int
  ) {
    Timber.d(
        "onTextChanged() called with: s = [" + s + "], start = [" + start + "], before = [" + before + "], count = [" + count + "]"
    )
  }

  override fun afterTextChanged(s: Editable) {
    editText.error = null
  }
}
