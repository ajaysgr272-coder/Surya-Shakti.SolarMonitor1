package com.example.surya_shaktisolarmonitor.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surya_shaktisolarmonitor.data.local.entity.EnergyLogEntity
import com.example.surya_shaktisolarmonitor.data.local.entity.ProfileEntity
import com.example.surya_shaktisolarmonitor.data.repository.EnergyRepository
import com.example.surya_shaktisolarmonitor.data.repository.GeminiRepository
import com.example.surya_shaktisolarmonitor.domain.usecase.CalculateSavingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: EnergyRepository,
    private val geminiRepository: GeminiRepository,
    private val calculateSavingsUseCase: CalculateSavingsUseCase
) : ViewModel() {

    val profile = repository.getProfile().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), null
    )

    val logs = repository.getAllLogs().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    private val _geminiTip = MutableStateFlow<String?>(null)
    val geminiTip: StateFlow<String?> = _geminiTip.asStateFlow()

    fun saveProfile(name: String, village: String, capacity: Double, rate: Double) {
        viewModelScope.launch {
            repository.saveProfile(ProfileEntity(householdName = name, village = village, panelCapacityKw = capacity, electricityRate = rate))
        }
    }

    fun logEnergy(generated: Double, consumed: Double, weather: String) {
        viewModelScope.launch {
            val rate = profile.value?.electricityRate ?: 1.0
            val savingsResult = calculateSavingsUseCase(generated, consumed, rate)
            
            val log = EnergyLogEntity(
                date = System.currentTimeMillis(),
                generatedKwh = generated,
                consumedKwh = consumed,
                weather = weather,
                savingsInInr = savingsResult.savingsInInr
            )
            repository.saveLog(log)
        }
    }

    fun getSmartTip(generated: Double, consumed: Double, weather: String) {
        viewModelScope.launch {
            _geminiTip.value = "Getting tips..."
            _geminiTip.value = geminiRepository.getEnergyTip(generated, consumed, weather)
        }
    }

    fun isPeakHour(): Boolean {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return hour in 11..14
    }
}
