package com.example.surya_shaktisolarmonitor.data.repository

import com.example.surya_shaktisolarmonitor.data.local.dao.EnergyDao
import com.example.surya_shaktisolarmonitor.data.local.entity.EnergyLogEntity
import com.example.surya_shaktisolarmonitor.data.local.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EnergyRepository @Inject constructor(
    private val energyDao: EnergyDao
) {
    fun getProfile(): Flow<ProfileEntity?> = energyDao.getProfile()

    suspend fun saveProfile(profile: ProfileEntity) = energyDao.insertProfile(profile)

    fun getAllLogs(): Flow<List<EnergyLogEntity>> = energyDao.getAllLogs()

    suspend fun saveLog(log: EnergyLogEntity) = energyDao.insertLog(log)

    suspend fun getLogByDate(date: Long) = energyDao.getLogByDate(date)
}
