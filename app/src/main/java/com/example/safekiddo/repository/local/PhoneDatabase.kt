package com.example.safekiddo.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.safekiddo.model.PhoneData
import com.example.safekiddo.repository.local.dao.PhoneDao

@Database(entities = arrayOf(PhoneData::class), version = 1, exportSchema = false)
abstract class PhoneDatabase : RoomDatabase() {
    abstract fun phoneDao(): PhoneDao
}