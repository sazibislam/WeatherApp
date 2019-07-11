package com.sazib.weatherapp.utils.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

class DividerItemDecoration : androidx.recyclerview.widget.RecyclerView.ItemDecoration {

  private var divider: Drawable? = null

  /**
   * Default divider will be used
   */
  constructor(context: Context) {
    val styledAttributes = context.obtainStyledAttributes(ATTRS)
    divider = styledAttributes.getDrawable(0)
    styledAttributes.recycle()
  }

  /**
   * Custom divider will be used
   */
  constructor(
    context: Context,
    resId: Int
  ) {
    divider = ContextCompat.getDrawable(context, resId)
  }

  override fun onDraw(
    c: Canvas,
    parent: androidx.recyclerview.widget.RecyclerView,
    state: androidx.recyclerview.widget.RecyclerView.State
  ) {
    val left = parent.paddingLeft
    val right = parent.width - parent.paddingRight

    val childCount = parent.childCount
    for (i in 0 until childCount) {
      val child = parent.getChildAt(i)

      val params = child.layoutParams as androidx.recyclerview.widget.RecyclerView.LayoutParams

      val top = child.bottom + params.bottomMargin
      val bottom = top + (divider?.intrinsicHeight ?: 0)

      divider?.setBounds(left, top, right, bottom)
      divider?.draw(c)
    }
  }

  companion object {

    private val ATTRS = intArrayOf(android.R.attr.listDivider)
  }
}
