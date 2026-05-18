package com.example.surya_shaktisolarmonitor.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey val id: Int = 1,
    val householdName: String,
    val village: String,
    val panelCapacityKw: Double,
    val electricityRate: Double
)
