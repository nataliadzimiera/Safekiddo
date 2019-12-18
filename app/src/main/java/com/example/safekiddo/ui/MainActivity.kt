package com.example.safekiddo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.safekiddo.R
import com.example.safekiddo.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
    }
}
