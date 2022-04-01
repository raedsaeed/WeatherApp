package com.raed.weatherapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.raed.weatherapp.R
import com.raed.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.doSomething()

        // setup toolbar
        setSupportActionBar(binding.tbActivityMainToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // setup navigation component
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.main_fragment))

        val hostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        navController = NavHostFragment.findNavController(hostFragment)

        binding.tbActivityMainToolbar.setupWithNavController(navController, appBarConfiguration)
    }
}