package com.example.safekiddo.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.safekiddo.model.PhoneData

@Dao
interface PhoneDao {
    @Query("SELECT * from phoneData")
    fun getAll(): List<PhoneData>

    @Insert(onConflict = REPLACE)
    fun insert(phoneData: PhoneData)

    @Query("DELETE from phoneData")
    fun deleteAll()
}