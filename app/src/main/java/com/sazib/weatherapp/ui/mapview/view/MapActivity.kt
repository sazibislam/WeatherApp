package com.sazib.weatherapp.ui.mapview.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sazib.weatherapp.R
import com.sazib.weatherapp.data.network.response.CityWeatherResponse.Main
import com.sazib.weatherapp.data.network.response.CityWeatherResponse.Weather
import com.sazib.weatherapp.data.network.response.CityWeatherResponse.Wind
import com.sazib.weatherapp.ui.base.view.DaggerActivity
import com.sazib.weatherapp.ui.mapview.interactor.MapMVPInteractor
import com.sazib.weatherapp.ui.mapview.presenter.MapPresenter
import com.sazib.weatherapp.utils.AppConstants
import kotlinx.android.synthetic.main.activity_map.toolbar
import kotlinx.android.synthetic.main.activity_map.tvCityName
import kotlinx.android.synthetic.main.activity_map.tvHumidity
import kotlinx.android.synthetic.main.activity_map.tvMaxTemp
import kotlinx.android.synthetic.main.activity_map.tvMinTemp
import kotlinx.android.synthetic.main.activity_map.tvTemperature
import kotlinx.android.synthetic.main.activity_map.tvWeatherType
import kotlinx.android.synthetic.main.activity_map.tvWindSpeed
import javax.inject.Inject

class MapActivity : DaggerActivity(), MapMVPView, OnMapReadyCallback/*, LocationListener */ {

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

    var lat = 0.0
    var lon = 0.0
    mMap = gMap

    presenter.getLat()
        ?.let { lat_ -> lat = lat_ }
    presenter.getLon()
        ?.let { lon_ -> lon = lon_ }

    val position = LatLng(lat, lon)
    mMap.addMarker(MarkerOptions().position(position).title(presenter.getCityName()))
    mMap.moveCamera(CameraUpdateFactory.newLatLng(position))

    //mMap.isMyLocationEnabled()

  }

  override fun onDestroy() {
    presenter.onDetach()
    super.onDestroy()
  }

  override fun updateInfo(
    weather: List<Weather>?,
    main: Main?,
    wind: Wind?
  ) {
    tvCityName.text = presenter.getCityName()
    tvWeatherType.text = weather?.get(0)
        ?.description
    main?.humidity?.let { humidity_ -> tvHumidity.text = "Humidity: $humidity_" }
    wind?.speed.let { wind_ -> tvWindSpeed.text = "Speed: $wind_" }
    main?.tempMax.let { max_ -> tvMaxTemp.text = "Max. temp: $max_" }
    main?.tempMin.let { min_ -> tvMinTemp.text = "Min. temp: $min_" }
    main?.temp?.let { temp_ -> tvTemperature.text = "${temp_.toInt() - 273} c" }
  }
}
