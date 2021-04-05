package com.dondja.dondja.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.epoxy.Carousel
import com.dondja.dondja.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Carousel.setDefaultGlobalSnapHelperFactory(null)
    }
}