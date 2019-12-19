package com.example.safekiddo.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phoneData")
data class PhoneData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "device_name") val deviceName: String,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "dimensions") val dimensions: String?,
    @ColumnInfo(name = "weight") val weight: String?,
    @ColumnInfo(name = "size") val size: String?,
    @ColumnInfo(name = "card_slot") val cardSlot: String?,
    @ColumnInfo(name = "loundspeaker") val loudspeaker: String?,
    @ColumnInfo(name = "wlan") val wlan: String?,
    @ColumnInfo(name = "bluetooth") val bluetooth: String?,
    @ColumnInfo(name = "gps") val gps: String?,
    @ColumnInfo(name = "radio") val radio: String?,
    @ColumnInfo(name = "usb") val usb: String?,
    @ColumnInfo(name = "messaging") val messaging: String?,
    @ColumnInfo(name = "browser") val browser: String?,
    @ColumnInfo(name = "java") val java: String?,
    @ColumnInfo(name = "colors") val colors: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
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
        parcel.writeInt(id)
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

    companion object CREATOR : Parcelable.Creator<PhoneData> {
        override fun createFromParcel(parcel: Parcel): PhoneData {
            return PhoneData(parcel)
        }

        override fun newArray(size: Int): Array<PhoneData?> {
            return arrayOfNulls(size)
        }
    }
}