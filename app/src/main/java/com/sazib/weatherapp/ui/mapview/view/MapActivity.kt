package com.sazib.weatherapp.ui.mapview.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sazib.weatherapp.R
import com.sazib.weatherapp.data.network.response.WeatherDataResponse
import com.sazib.weatherapp.ui.base.view.DaggerActivity
import com.sazib.weatherapp.ui.mapview.interactor.MapMVPInteractor
import com.sazib.weatherapp.ui.mapview.presenter.MapPresenter
import kotlinx.android.synthetic.main.activity_map.toolbar
import javax.inject.Inject

class MapActivity : DaggerActivity(), MapMVPView, OnMapReadyCallback {

    @Inject
    lateinit var presenter: MapPresenter<MapMVPView, MapMVPInteractor>

    private var data: WeatherDataResponse.ListData? = null

    companion object {
        const val DATA = "map_data"
        fun getStartIntent(
            context: Context,
            data: WeatherDataResponse.ListData
        ): Intent {
            val intent = Intent(context, MapActivity::class.java)
            //intent.putExtra(DATA, data)
            return intent
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

/*    val mapFragment = supportFragmentManager
        .findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(this)*/

    }

    override fun onMapReady(gMap: GoogleMap) {

        mMap = gMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}
