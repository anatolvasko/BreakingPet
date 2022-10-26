package com.example.breakingpet.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.example.breakingpet.R
import com.example.breakingpet.databinding.ActivityMainBinding
import com.example.breakingpet.presentation.fragments.*
import com.example.breakingpet.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //splash screen
        installSplashScreen().apply { setKeepOnScreenCondition { viewModel.isLoading.value } }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment())
            .commit()

        binding.bottomNavigationView.selectedItemId = R.id.home

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    supportActionBar?.title = "Home"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment()).commit()
                }
                R.id.characters -> {
                    supportActionBar?.title = "Characters"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, CharactersFragment()).commit()
                }
                R.id.episodes -> {
                    supportActionBar?.title = "Episodes"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, EpisodesFragment()).commit()
                }
                R.id.quotes -> {
                    supportActionBar?.title = "Quotes"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, QuotesFragment()).commit()
                }
                R.id.death -> {
                    supportActionBar?.title = "Deaths"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, DeathsFragment()).commit()
                }

            }
            true
        }

        /*viewModel.getCharactersList()

        viewModel.allCharacters.observe(this, Observer {
            Toast.makeText(this, it[0].name, Toast.LENGTH_LONG).show()
        })*/
    }


}


