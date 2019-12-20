package com.example.safekiddo

import com.example.safekiddo.di.AppComponent
import com.example.safekiddo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication(), HasActivityInjector {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val builder: AppComponent.Builder =
            DaggerAppComponent.builder().application(this)
        builder.seedInstance(this)
        return builder.build().androidInjector()
    }

}