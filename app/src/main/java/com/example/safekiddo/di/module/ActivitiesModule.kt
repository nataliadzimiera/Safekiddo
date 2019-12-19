package com.example.safekiddo.di.module

import com.example.safekiddo.di.ActivityScope
import com.example.safekiddo.ui.detailsActivity.DetailsActivity
import com.example.safekiddo.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivitiesModule {
    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun detailsActivity(): DetailsActivity

}