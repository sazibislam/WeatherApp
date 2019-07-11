package com.sazib.weatherapp.ui.citylist.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sazib.weatherapp.R
import com.sazib.weatherapp.ui.base.view.DaggerActivity
import com.sazib.weatherapp.ui.citylist.interactor.CityListMVPInteractor
import com.sazib.weatherapp.ui.citylist.presenter.CityListPresenter
import com.sazib.weatherapp.ui.citylist.view.adapter.CityListAdapter
import com.sazib.weatherapp.ui.citylist.view.adapter.CityListAdapter.Callback
import com.sazib.weatherapp.ui.citylist.view.model.CityListDataModel
import com.sazib.weatherapp.ui.mapview.view.MapActivity
import com.sazib.weatherapp.utils.AppDataUtils
import com.sazib.weatherapp.utils.logger.AppLogger
import kotlinx.android.synthetic.main.activity_city_list.rvCityList
import kotlinx.android.synthetic.main.activity_city_list.toolbar
import javax.inject.Inject

class CityListActivity : DaggerActivity(), CityListMVPView, Callback {

  @Inject lateinit var presenter: CityListPresenter<CityListMVPView, CityListMVPInteractor>
  @Inject lateinit var adapter: CityListAdapter
  @Inject lateinit var layoutManager: LinearLayoutManager

  private var isLoading = false

  var locationManager: LocationManager? = null
  var mprovider: String? = null

  companion object {
    fun getStartIntent(context: Context): Intent = Intent(context, CityListActivity::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_city_list)
    presenter.onAttach(this)
  }

  override fun initView() {
    setupToolbar(toolbar, "Weather App")

    adapter = CityListAdapter()

    rvCityList.layoutManager = layoutManager
    rvCityList.itemAnimator = DefaultItemAnimator()
    rvCityList.adapter = adapter
    adapter.setCallback(this)

    //getLocation()

    //presenter.getCityListData()

    setAdapterData(AppDataUtils.getCityListData(applicationContext))
  }

  private fun getLocation() {
    locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val criteria = Criteria()

    mprovider = locationManager?.getBestProvider(criteria, false)

    if (mprovider != null && !mprovider.equals("")) {

      if (ActivityCompat.checkSelfPermission(
              this, Manifest.permission.ACCESS_FINE_LOCATION
          ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
              this, Manifest.permission.ACCESS_COARSE_LOCATION
          ) != PackageManager.PERMISSION_GRANTED
      ) {
        return
      }
      val location = locationManager?.getLastKnownLocation(mprovider)

      AppLogger.d(location)

    }
  }

  //data setup from Api calling
  //override fun setAdapterData(data_: List<CityList>) = adapter.addDataToList(data_)
  override fun setAdapterData(data_: List<CityListDataModel>) = adapter.addDataToList(data_)

  //for data pagination
  // override fun setAdapterPage(page_: Int) {}

  override fun getIsLoading(): Boolean = isLoading

  override fun setIsLoading(isLoading: Boolean) {
    this.isLoading = isLoading
  }

  override fun onDestroy() {
    presenter.onDetach()
    super.onDestroy()
  }

  override fun click(data: CityListDataModel) {
    //start map activity
    startActivity(MapActivity.getStartIntent(applicationContext))
    AppLogger.d(data)
  }
}
