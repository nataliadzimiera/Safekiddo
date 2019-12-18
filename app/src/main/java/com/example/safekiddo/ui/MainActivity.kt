package com.example.safekiddo.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.example.safekiddo.R
import com.example.safekiddo.databinding.ActivityMainBinding
import com.example.safekiddo.di.ViewModelInjectionFactory
import com.example.safekiddo.repository.local.PhoneDatabase
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelInjectionFactory<MainActivityViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityViewModel = ViewModelProviders.of(this, viewModelInjectionFactory)
            .get(MainActivityViewModel::class.java)
        val db = Room.databaseBuilder(this, PhoneDatabase::class.java, "database-phone").build()
    }
}
