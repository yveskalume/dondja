
package com.dondja.dondja.ui.activity.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dondja.dondja.R
import com.dondja.dondja.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAuthBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}