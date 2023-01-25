package com.example.breakingpet.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import com.example.breakingpet.R
import com.example.breakingpet.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.type.LatLng


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapBinding
    private lateinit var map: GoogleMap


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bnv = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val drawerLayout = activity?.findViewById<DrawerLayout>(R.id.drawerLayout)
        drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        bnv?.isVisible = false

        with(binding){
            mapContainer.onCreate(savedInstanceState)
            mapContainer.onResume()
            mapContainer.getMapAsync(this@MapFragment)
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {


        val albuquerque = com.google.android.gms.maps.model.LatLng(35.08449, -106.65114)
        googleMap.addMarker(MarkerOptions().position(albuquerque).title("Albuquerque"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(albuquerque))
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(5.0F))
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isIndoorLevelPickerEnabled
    }

    override fun onPause() {
        super.onPause()
        val bnv = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bnv?.isVisible = true

        val drawerLayout = activity?.findViewById<DrawerLayout>(R.id.drawerLayout)
        drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED)
    }
}