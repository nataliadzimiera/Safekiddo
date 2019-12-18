package com.example.safekiddo.repository.remote

import com.example.safekiddo.model.PhoneDetails
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("v1/getdevice")
    fun getPhonesList(
        @Query("token") token: String,
        @Query("device") device: String,
        @Query("brand") brand: String
    ): Observable<PhoneDetails>
}