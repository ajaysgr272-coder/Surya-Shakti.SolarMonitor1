package com.example.surya_shaktisolarmonitor.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "energy_logs")
data class EnergyLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: Long, // timestamp
    val generatedKwh: Double,
    val consumedKwh: Double,
    val weather: String, // Sunny or Cloudy
    val savingsInInr: Double
)
