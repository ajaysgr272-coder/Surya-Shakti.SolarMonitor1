package com.example.surya_shaktisolarmonitor.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.surya_shaktisolarmonitor.data.local.dao.EnergyDao
import com.example.surya_shaktisolarmonitor.data.local.entity.EnergyLogEntity
import com.example.surya_shaktisolarmonitor.data.local.entity.ProfileEntity

@Database(entities = [ProfileEntity::class, EnergyLogEntity::class], version = 1, exportSchema = false)
abstract class EnergyDatabase : RoomDatabase() {
    abstract fun energyDao(): EnergyDao
}
