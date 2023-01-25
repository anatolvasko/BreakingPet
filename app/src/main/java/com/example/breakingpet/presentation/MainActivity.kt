package com.example.breakingpet.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.breakingpet.R
import com.example.breakingpet.databinding.ActivityMainBinding
import com.example.breakingpet.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //splash screen
        installSplashScreen().apply { setKeepOnScreenCondition { viewModel.isLoading.value } }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHost.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration =
            AppBarConfiguration(
                navController.graph,
                binding.drawerLayout
            )

        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        setupWithNavController(binding.drawerNavigation, navController)


        binding.drawerNavigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.location_albuquerque -> {

                        navController.navigate(R.id.action_to_map_fragment)
                        //supportFragmentManager.beginTransaction().add(R.id.fragment_container, MapFragment()).commit()
                        binding.drawerLayout.closeDrawer(GravityCompat.START)


                }

            }
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }

}


