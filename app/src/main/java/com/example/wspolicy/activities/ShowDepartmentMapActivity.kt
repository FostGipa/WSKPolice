package com.example.wspolicy.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.wspolicy.R
import com.example.wspolicy.databinding.ActivityShowDepartmentMapBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.DrivingOptions
import com.yandex.mapkit.directions.driving.DrivingRoute
import com.yandex.mapkit.directions.driving.DrivingRouterType
import com.yandex.mapkit.directions.driving.DrivingSession
import com.yandex.mapkit.directions.driving.VehicleOptions
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

class ShowDepartmentMapActivity : AppCompatActivity() {

    private lateinit var binding : ActivityShowDepartmentMapBinding
    private lateinit var mapView : MapView
    private var startLocation = Point()
    private lateinit var mapObjectCollection: MapObjectCollection
    private lateinit var placemarkMapObject: PlacemarkMapObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("15f1c5e6-b50b-49f5-a491-57f675d736e7")
        MapKitFactory.initialize(this)
        binding = ActivityShowDepartmentMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Show deparment"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mapView = findViewById(R.id.deparmentMapView)

        val coordsStr = intent.getStringExtra("departmentCoords").toString()

        val (latitude, longitude) = coordsStr
            .substring(1, coordsStr.length - 1)
            .split(", ")
            .map { it.toDouble() }

        startLocation = Point(latitude, longitude)

        binding.deparmentMapView.map.move(
            CameraPosition(startLocation,
                /* zoom = */ 17.0f,
                /* azimuth = */ 150.0f,
                /* tilt = */ 30.0f
            )
        )
        setMarkerInStartLocation()
        placemarkMapObject.addTapListener(mapObjectTapListener)
    }

    private fun setMarkerInStartLocation() {
        mapObjectCollection = binding.deparmentMapView.map.mapObjects
        placemarkMapObject = mapObjectCollection.addPlacemark(startLocation, ImageProvider.fromResource(this, R.drawable.ic_pin))
    }

    private val mapObjectTapListener = object : MapObjectTapListener {
        override fun onMapObjectTap(mapObject: MapObject, point: Point): Boolean{

            return true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}