package com.dondja.dondja.ui.fragment.splash

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentSplashBinding
import com.dondja.dondja.ui.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding by viewBinding<FragmentSplashBinding>()

    private val currentUser by lazy { FirebaseAuth.getInstance().currentUser }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSplash()
    }

    private fun setUpSplash() {
       lifecycleScope.launch() {
           if (currentUser == null) {
               delay(2000L)
//               val directions = SplashFragmentDirections.toWelcomeFragment()
//               findNavController().navigate(directions)
           }
           else if (currentUser != null) {
               delay(2000L)
               val directions = SplashFragmentDirections.toFeedFragment()
               findNavController().navigate(directions)
           }
//        else if (currentUser != null) {
//               delay(2000L)
//               startActivity(Intent(requireContext(),MainActivity::class.java))
//           }
       }
    }
}