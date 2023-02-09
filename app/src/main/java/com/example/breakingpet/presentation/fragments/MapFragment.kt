package com.example.breakingpet.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.breakingpet.R
import com.example.breakingpet.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView?.isVisible = false

        val drawerLayout = activity?.findViewById<DrawerLayout>(R.id.drawerLayout)
        drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        with(binding) {
            mapContainer.onCreate(savedInstanceState)
            mapContainer.onResume()
            mapContainer.getMapAsync(this@MapFragment)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        val albuquerque = com.google.android.gms.maps.model.LatLng(35.08449, -106.65114)

        with(googleMap) {
            addMarker(MarkerOptions().position(albuquerque).title("Albuquerque"))
            moveCamera(CameraUpdateFactory.newLatLng(albuquerque))
            animateCamera(CameraUpdateFactory.zoomTo(5.0F))
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isIndoorLevelPickerEnabled
        }
    }

    override fun onPause() {
        super.onPause()

        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView?.isVisible = true

        val drawerLayout = activity?.findViewById<DrawerLayout>(R.id.drawerLayout)
        drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED)
    }
}