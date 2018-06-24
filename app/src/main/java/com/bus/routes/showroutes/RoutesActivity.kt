package com.bus.routes.showroutes

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
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
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_map.*
import java.util.*
import javax.inject.Inject


class RoutesActivity : AppCompatActivity(), OnMapReadyCallback, RoutesActivityContract.View, RoutesListAdapter.RouteItemListener {
    override fun showErrorLoadRoutesList() {

        Toast.makeText(this,getString(R.string.msg_failure_routes),Toast.LENGTH_SHORT).show();
    }

    override fun showErrorLoadStops() {
        Toast.makeText(this,getString(R.string.msg_failure_stops),Toast.LENGTH_SHORT).show();
    }


    @Inject
    lateinit var mPresenter: RoutesActivityPresenter
    lateinit var adapter: RoutesListAdapter
    private var activityComponent: ActivityComponent? = null
    var mPolylines = ArrayList<Polyline>()
    private lateinit var mMap: GoogleMap

    override fun showStopsByRoute(stopsArrayList: ArrayList<Stop>) {
        clearMap()
        val points: ArrayList<LatLng> = ArrayList()
        val lineOptions = PolylineOptions()
        for (stop in stopsArrayList) {
            val position = LatLng(stop.lat, stop.lng)
            points.add(position)
            mMap.addMarker(MarkerOptions().position(position))
        }
        lineOptions.addAll(points)
        lineOptions.width(12f)
        lineOptions.color(Color.BLACK)
        mPolylines.add(this.mMap.addPolyline(lineOptions))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(stopsArrayList.first().lat, stopsArrayList.first().lng), 16.0f))

    }


    override fun onItemClick(route: Route) {
       panel_route_selected.visibility=View.VISIBLE
        lab_route.text = String.format("%s%s%s",route.name,",",route.description)
        val bottomSheetBehavior = BottomSheetBehavior.from<View>(bottom_sheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        mPresenter.getStops(route.stops_url)
    }


    override fun showSchoolsList(schoolArrayList: ArrayList<Route>) {
        adapter.updateList(schoolArrayList)
    }

    public override fun onResume() {
        super.onResume()
        mPresenter.takeView(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent()!!.inject(this)
        setContentView(R.layout.activity_main)
        initViews()
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
        setDefaultPositionInMap()
    }

    fun clearMap(){
        for (mPolyline in mPolylines) {
            mPolyline.remove()
        }
        mPolylines.clear()
        mMap.clear()
    }
    fun initViews(){
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        adapter = RoutesListAdapter(this)
        recycler_routes.layoutManager = LinearLayoutManager(this)
        recycler_routes.adapter = adapter
        img_close.setOnClickListener {
            clearMap()
            panel_route_selected.visibility=View.GONE
            setDefaultPositionInMap()

        }
    }


    fun setDefaultPositionInMap(){
        // Add a marker in Bogotá and move the camera
        val bogota = LatLng(4.6393866, -74.1435257)
        // mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bogota, 11.0f))
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
