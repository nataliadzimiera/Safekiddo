package com.example.safekiddo.di.module

import android.app.Application
import androidx.room.Room
import com.example.safekiddo.repository.local.PhoneDatabase
import com.example.safekiddo.repository.remote.Api
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DomainToolsModule {
    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okhttp: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okhttp)
            .baseUrl("https://fonoapi.freshpixl.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    internal fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    internal fun provideDb(app: Application): PhoneDatabase {
        return Room.databaseBuilder(app, PhoneDatabase::class.java, "database-phone")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}