package com.sazib.weatherapp.ui.base.view

import android.view.View

abstract class BaseViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(
    itemView
) {

  private var currentPosition: Int = 0

  abstract fun clear()

  open fun onBind(position: Int) {
    currentPosition = position
    clear()
  }
}
