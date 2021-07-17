package com.dondja.dondja.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.airbnb.epoxy.Carousel
import com.dondja.dondja.R
import com.dondja.dondja.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val navController by lazy {
        Navigation.findNavController(this,R.id.fragmentHost)
    }

    private val binding by viewBinding<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpBottomNav()
        setUpNavigation()
        Carousel.setDefaultGlobalSnapHelperFactory(null)
    }

    private fun setUpNavigation() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.isVisible = when(destination.id) {
                R.id.storyViewFragment -> false;
                R.id.dialogStoryViewersFragment -> false;
                R.id.postViewFragment -> false
                else -> true
            }
        }
    }

    private fun setUpBottomNav() {
        binding.bottomNavigation.setupWithNavController(navController)
    }
}