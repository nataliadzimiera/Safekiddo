package com.example.safekiddo.di

import dagger.releasablereferences.CanReleaseReferences
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
@CanReleaseReferences
annotation class ActivityScope