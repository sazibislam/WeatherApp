package com.sazib.weatherapp.ui.mapview.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.sazib.weatherapp.R
import com.sazib.weatherapp.ui.base.view.DaggerActivity
import com.sazib.weatherapp.ui.mapview.interactor.MapMVPInteractor
import com.sazib.weatherapp.ui.mapview.presenter.MapPresenter
import com.sazib.weatherapp.utils.AppConstants
import kotlinx.android.synthetic.main.activity_map.toolbar
import javax.inject.Inject

class MapActivity : DaggerActivity(), MapMVPView, OnMapReadyCallback, LocationListener {

  @Inject lateinit var presenter: MapPresenter<MapMVPView, MapMVPInteractor>

  companion object {
    fun getStartIntent(
      context: Context
    ): Intent {
      return Intent(context, MapActivity::class.java)
    }
  }

  private lateinit var mMap: GoogleMap

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_map)
    presenter.onAttach(this)
  }

  override fun initView() {

    setupToolbarBack(toolbar, "Weather App")

    val mapFragment =
      supportFragmentManager.findFragmentById(R.id.mapViewFragment) as? SupportMapFragment
    mapFragment?.getMapAsync(this)

    //checkMapPermission()
  }

  private fun checkMapPermission() {
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
      return
    }
  }

  override fun onMapReady(gMap: GoogleMap) {

    mMap = gMap
/*
    // Add a marker in Sydney and move the camera
    val sydney = LatLng(-23.68, 90.35)
    mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/

    //mMap.isMyLocationEnabled()

  }

  override fun onLocationChanged(location: Location?) {
    //mMap.clear()
  }

  override fun onStatusChanged(
    provider: String?,
    status: Int,
    extras: Bundle?
  ) {

  }

  override fun onProviderEnabled(provider: String?) {

  }

  override fun onProviderDisabled(provider: String?) {

  }

  override fun onDestroy() {
    presenter.onDetach()
    super.onDestroy()
  }
}
