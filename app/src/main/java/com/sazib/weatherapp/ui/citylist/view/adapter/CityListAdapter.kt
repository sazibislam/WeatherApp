package com.sazib.weatherapp.ui.citylist.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sazib.weatherapp.R
import com.sazib.weatherapp.ui.base.view.BaseViewHolder
import com.sazib.weatherapp.ui.citylist.view.model.CityListDataModel
import com.sazib.weatherapp.utils.logger.AppLogger
import kotlinx.android.synthetic.main.list_item_city.view.tvCityName
import kotlinx.android.synthetic.main.list_item_city.view.tvTemperature
import kotlinx.android.synthetic.main.list_item_city.view.tvType

class CityListAdapter(private var data: MutableList<CityListDataModel> = ArrayList()) :
    RecyclerView.Adapter<BaseViewHolder>() {

  private var callback: Callback? = null

  override fun getItemCount() = data.size

  override fun onBindViewHolder(
    holder: BaseViewHolder,
    position: Int
  ) = holder.onBind(position)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): BaseViewHolder = ViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.list_item_city, parent, false)
  )

  internal fun addDataToList(data: List<CityListDataModel>) {
    this.data.clear()
    this.data.addAll(data)
    notifyDataSetChanged()
    AppLogger.d(data)
  }

  internal fun setCallback(callback: Callback) {
    this.callback = callback
  }

  inner class ViewHolder(view: View) : BaseViewHolder(view) {

    override fun clear() {
      itemView.tvCityName.text = ""
      itemView.tvType.text = ""
      itemView.tvTemperature.text = ""
    }

    override fun onBind(position: Int) {
      val model = data[position]

      model.name?.let { name_ ->
        itemView.tvCityName.text = name_
      }
      model.weatherType?.let { type ->
        itemView.tvType.text = type
      }
      model.temperature?.let { temperature ->
        itemView.tvTemperature.text = temperature
      }

      itemView.setOnClickListener { callback?.click(model) }

    }
  }

  interface Callback {
    fun click(data: CityListDataModel)
  }
}