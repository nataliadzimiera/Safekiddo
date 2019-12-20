package com.example.safekiddo.di

import android.app.Application
import com.example.safekiddo.App
import com.example.safekiddo.di.module.ActivitiesModule
import com.example.safekiddo.di.module.DomainToolsModule
import com.example.safekiddo.di.module.SessionModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SessionModule::class,
        DomainToolsModule::class,
        ActivitiesModule::class,
        AndroidSupportInjectionModule::class
    ]
)


interface AppComponent : AndroidInjector<App> {
    fun androidInjector(): AndroidInjector<App>

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {
        @BindsInstance
        abstract fun application(application: Application): Builder

        abstract override fun build(): AppComponent
    }
}