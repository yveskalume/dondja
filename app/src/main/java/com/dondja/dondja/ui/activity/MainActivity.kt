package com.dondja.dondja.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.airbnb.epoxy.Carousel
import com.dondja.dondja.R
import com.dondja.dondja.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val navController by lazy {
        Navigation.findNavController(this,R.id.fragmentHost)
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Carousel.setDefaultGlobalSnapHelperFactory(null)
        setUpBottomNav()
    }

    private fun setUpBottomNav() {
        binding.bottomNavigation.setupWithNavController(navController)
    }
}