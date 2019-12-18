package com.example.safekiddo.model

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
)