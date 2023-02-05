package com.example.breakingpet.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import com.example.breakingpet.R
import com.example.breakingpet.databinding.ActivityMainBinding
import com.example.breakingpet.presentation.viewmodel.MainViewModel
import com.example.breakingpet.utils.UpdateDbWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

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

        //Setup Navigation
        setupNavigation()

        //WorkManager updates database
        updateDatabaseByWorker()

        //Handle onBackPressed
        onBackPressedDispatch()

        //configure exit button
        configureExitButton()

    }

    private fun configureExitButton() {
        binding.exitView.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            val dialog = Dialog(this)
            with(dialog) {
                setContentView(R.layout.dialog_layout)
                window?.setBackgroundDrawableResource(R.color.transparent)
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                setCancelable(false)
            }
            dialog.show()

            val cancelButton = dialog.findViewById<TextView>(R.id.dialog_cancel_view)
            val yesButton = dialog.findViewById<TextView>(R.id.dialog_yes_view)

            cancelButton.setOnClickListener { dialog.cancel() }

            yesButton.setOnClickListener { finish() }

        }
    }

    private fun onBackPressedDispatch() {

        val drawerCallback =
            object : OnBackPressedCallback(binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                override fun handleOnBackPressed() {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
            }

        binding.drawerLayout.addDrawerListener(
            object : DrawerLayout.SimpleDrawerListener() {
                override fun onDrawerClosed(drawerView: View) {
                    drawerCallback.isEnabled = false
                }

                override fun onDrawerOpened(drawerView: View) {
                    drawerCallback.isEnabled = true
                }
            })
        onBackPressedDispatcher.addCallback(this, drawerCallback)
    }

    private fun setupNavigation() {

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHost.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)

        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        setupWithNavController(binding.drawerNavigation, navController)


        binding.drawerNavigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.location_albuquerque -> {
                    navController.navigate(R.id.action_to_map_fragment)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            true
        }
        binding.drawerLayout.descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS


    }

    private fun updateDatabaseByWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val updateDBWorkRequest = PeriodicWorkRequest.Builder(
            UpdateDbWorker::class.java,
            24,
            TimeUnit.HOURS
        )
            .setInitialDelay(24, TimeUnit.HOURS)
            .setConstraints(constraints)
            .addTag("update_database")
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "update_database",
            ExistingPeriodicWorkPolicy.KEEP,
            updateDBWorkRequest
        )
    }

    override fun onSupportNavigateUp(): Boolean {

        return if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        } else {
            NavigationUI.navigateUp(
                navController,
                appBarConfiguration
            ) || super.onSupportNavigateUp()
        }
    }
}




