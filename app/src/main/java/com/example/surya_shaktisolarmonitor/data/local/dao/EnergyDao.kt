package com.example.surya_shaktisolarmonitor.data.local.dao

import androidx.room.*
import com.example.surya_shaktisolarmonitor.data.local.entity.EnergyLogEntity
import com.example.surya_shaktisolarmonitor.data.local.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EnergyDao {
    @Query("SELECT * FROM profile WHERE id = 1")
    fun getProfile(): Flow<ProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity)

    @Query("SELECT * FROM energy_logs ORDER BY date DESC")
    fun getAllLogs(): Flow<List<EnergyLogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: EnergyLogEntity)

    @Query("SELECT * FROM energy_logs WHERE date = :date LIMIT 1")
    suspend fun getLogByDate(date: Long): EnergyLogEntity?
}
