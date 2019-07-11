package com.sazib.weatherapp.ui.mapview.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.sazib.weatherapp.R
import com.sazib.weatherapp.ui.base.view.DaggerActivity
import kotlinx.android.synthetic.main.activity_map.toolbar

class MapActivity : DaggerActivity(), MapMVPView, OnMapReadyCallback {

  companion object {
    fun getStartIntent(context: Context): Intent = Intent(context, MapActivity::class.java)
  }

  private lateinit var mMap: GoogleMap

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_map)

  }

  override fun initView() {

    setupToolbarBack(toolbar, "Weather App")

    /*  val mapFragment = supportFragmentManager
          .findFragmentById(R.id.map) as SupportMapFragment
      mapFragment.getMapAsync(this)*/
  }

  override fun onMapReady(gMap: GoogleMap) {

    /*  mMap = gMap

      // Add a marker in Sydney and move the camera
      val sydney = LatLng(-34.0, 151.0)
      mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
      mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
  */
  }
}
