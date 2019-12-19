package com.example.safekiddo.model

import android.os.Parcel
import android.os.Parcelable
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
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(deviceName)
        parcel.writeString(brand)
        parcel.writeString(dimensions)
        parcel.writeString(weight)
        parcel.writeString(size)
        parcel.writeString(cardSlot)
        parcel.writeString(loudspeaker)
        parcel.writeString(wlan)
        parcel.writeString(bluetooth)
        parcel.writeString(gps)
        parcel.writeString(radio)
        parcel.writeString(usb)
        parcel.writeString(messaging)
        parcel.writeString(browser)
        parcel.writeString(java)
        parcel.writeString(colors)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PhoneDetails> {
        override fun createFromParcel(parcel: Parcel): PhoneDetails {
            return PhoneDetails(parcel)
        }

        override fun newArray(size: Int): Array<PhoneDetails?> {
            return arrayOfNulls(size)
        }
    }
}