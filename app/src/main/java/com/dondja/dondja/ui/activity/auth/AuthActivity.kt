
package com.dondja.dondja.ui.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.dondja.dondja.R
import com.dondja.dondja.databinding.ActivityAuthBinding
import com.dondja.dondja.ui.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private val viewModel by viewModels<AuthViewModel>()
    val auth by lazy { FirebaseAuth.getInstance() }

    private val binding by lazy {
        ActivityAuthBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (auth.currentUser != null) {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}