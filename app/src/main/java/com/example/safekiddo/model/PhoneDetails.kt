package com.example.safekiddo.model

import com.google.gson.annotations.SerializedName

data class PhoneDetails(
    @SerializedName("DeviceName")
    val deviceName: String,
    @SerializedName("Brand")
    val brand: String,
    val dimensions: String?,
    val weight: String?,
    val size: String?,
    @SerializedName("card_slot")
    val cardSlot: String?,
    @SerializedName("loudspeaker_")
    val loudspeaker: String?,
    val wlan: String?,
    val bluetooth: String?,
    val gps: String?,
    val radio: String?,
    val usb: String?,
    val messaging: String?,
    val browser: String?,
    val java: String?,
    val colors: String?
)