package com.example.safekiddo.di

import com.example.safekiddo.App
import com.example.safekiddo.di.module.ActivitiesModule
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@SessionScope
@Subcomponent(
    modules = [
        ActivitiesModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface SessionComponent : AndroidInjector<App> {
    @Subcomponent.Builder
    abstract class Builder {
        abstract fun build(): SessionComponent
    }
}