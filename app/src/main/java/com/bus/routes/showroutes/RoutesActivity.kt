package com.bus.routes.showroutes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bus.routes.R
import com.bus.routes.application.RoutesApplication
import com.bus.routes.component.ActivityComponent
import com.bus.routes.component.DaggerActivityComponent
import com.bus.routes.domain.Route
import com.bus.routes.domain.Stop
import com.bus.routes.module.ActivityModule

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import javax.inject.Inject


class RoutesActivity : AppCompatActivity(), OnMapReadyCallback, RoutesActivityContract.View ,RoutesListAdapter.RouteItemListener{
    override fun showStopsByRoute(stopsArrayList: ArrayList<Stop>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemClick(route: Route) {
            mPresenter.getStops(route.stops_url)
    }

    @Inject
    lateinit var mPresenter: RoutesActivityPresenter

    lateinit var adapter: RoutesListAdapter
    private var activityComponent: ActivityComponent? = null


    override fun showSchoolsList(schoolArrayList: ArrayList<Route>) {
        adapter.updateList(schoolArrayList)
    }

    public override fun onResume() {
        super.onResume()
        mPresenter.takeView(this)

    }

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent()!!.inject(this)

        setContentView(R.layout.activity_main)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        adapter = RoutesListAdapter(this)
        recycler_routes.layoutManager = LinearLayoutManager(this)
        recycler_routes.adapter = adapter

    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Bogotá and move the camera
        val bogota = LatLng(4.6482837, -74.2478958)
        // mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bogota, 10.0f))
    }
    fun getActivityComponent(): ActivityComponent? {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(ActivityModule(this))
                    .applicationComponent(RoutesApplication.appComponent)
                    .build()
        }
        return activityComponent
    }
}
