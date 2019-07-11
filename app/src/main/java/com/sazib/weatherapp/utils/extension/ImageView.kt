package com.sazib.weatherapp.utils.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

internal fun ImageView.loadImage(url: String) {
  Glide.with(this.context)
      .load(url)
      .asBitmap()
      .centerCrop()
      .into(this)
}

internal fun ImageView.loadResourceImage(resource: Int) {
  Glide.with(this.context)
      .load(resource)
      .asBitmap()
      .fitCenter()
      .into(this)
}