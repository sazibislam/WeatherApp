package com.sazib.weatherapp.ui.citylist.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.sazib.weatherapp.R
import com.sazib.weatherapp.data.network.response.WeatherDataResponse
import com.sazib.weatherapp.ui.base.view.DaggerActivity
import com.sazib.weatherapp.ui.citylist.interactor.CityListMVPInteractor
import com.sazib.weatherapp.ui.citylist.presenter.CityListPresenter
import com.sazib.weatherapp.ui.citylist.view.adapter.CityListAdapter
import com.sazib.weatherapp.ui.citylist.view.adapter.CityListAdapter.Callback
import com.sazib.weatherapp.ui.mapview.view.MapActivity
import com.sazib.weatherapp.utils.AppConstants
import com.sazib.weatherapp.utils.GpsUtils
import kotlinx.android.synthetic.main.activity_city_list.rvCityList
import kotlinx.android.synthetic.main.activity_city_list.toolbar
import javax.inject.Inject

class CityListActivity : DaggerActivity(), CityListMVPView, Callback {

  @Inject lateinit var presenter: CityListPresenter<CityListMVPView, CityListMVPInteractor>
  @Inject lateinit var adapter: CityListAdapter
  @Inject lateinit var layoutManager: LinearLayoutManager

  private var isLoading = false

  private var mFusedLocationClient: FusedLocationProviderClient? = null

  private var wayLatitude = 0.0
  private var wayLongitude = 0.0
  private var locationRequest: LocationRequest? = null
  private var locationCallback: LocationCallback? = null

  private val isContinue = false
  private var isGPS = false

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

    rvCityList.apply {
      layoutManager = this@CityListActivity.layoutManager
      rvCityList.itemAnimator = DefaultItemAnimator()
      addItemDecoration(decoration)
      adapter = this@CityListActivity.adapter
    }

    adapter.setCallback(this)

  }

  override fun checkGps() {

    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    val locationRequest = LocationRequest.create()
    locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    locationRequest.interval = 10 * 1000 // 10 seconds
    locationRequest.fastestInterval = 5 * 1000 // 5 seconds

    GpsUtils(this).turnGPSOn { isGPSEnable ->
      isGPS = isGPSEnable
    }

    locationCallback = object : LocationCallback() {
      override fun onLocationResult(locationResult: LocationResult?) {
        if (locationResult == null) {
          return
        }
        for (location in locationResult.locations) {
          if (location != null) {
            wayLatitude = location.latitude
            wayLongitude = location.longitude
            if (!isContinue && mFusedLocationClient != null) {
              mFusedLocationClient?.removeLocationUpdates(locationCallback)
            }
          }
        }
      }
    }
    getLocation()
  }

  override fun getLocation() {
    if (ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
      ActivityCompat.requestPermissions(
          this, arrayOf(
          Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
      ), AppConstants.LOCATION_REQUEST
      )
    } else {
      if (isContinue) {
        mFusedLocationClient?.requestLocationUpdates(locationRequest, locationCallback, null)
      } else {
        mFusedLocationClient?.lastLocation?.addOnSuccessListener(this) { location ->
          if (location != null) {
            wayLatitude = location.latitude
            wayLongitude = location.longitude
            setLocation(location.latitude.toString(), location.longitude.toString())
          } else {
            mFusedLocationClient?.requestLocationUpdates(
                locationRequest, locationCallback, null
            )
          }
        }
      }
    }
  }

  @SuppressLint("MissingPermission")
  override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    when (requestCode) {
      1000 -> {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

          if (isContinue) {
            mFusedLocationClient?.requestLocationUpdates(locationRequest, locationCallback, null)
          } else {
            mFusedLocationClient?.lastLocation
                ?.addOnSuccessListener(this) { location ->
                  if (location != null) {
                    wayLatitude = location.latitude
                    wayLongitude = location.longitude
                    setLocation(location.latitude.toString(), location.longitude.toString())
                  } else {
                    mFusedLocationClient?.requestLocationUpdates(
                        locationRequest, locationCallback, null
                    )
                  }
                }
          }
        } else {
          showMessage("Permission denied")
        }
      }
    }
  }

  override fun onActivityResult(
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == Activity.RESULT_OK) {
      if (requestCode == AppConstants.GPS_REQUEST) {
        isGPS = true
      }
    }
  }

  override fun setAdapterData(data_: List<WeatherDataResponse.ListData>) =
    adapter.addDataToList(data_)

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

  override fun setLocation(
    latitude: String,
    longitude: String
  ) = presenter.setLocation(latitude, longitude)

  override fun click() {
    startActivity(MapActivity.getStartIntent(applicationContext))
  }
}
