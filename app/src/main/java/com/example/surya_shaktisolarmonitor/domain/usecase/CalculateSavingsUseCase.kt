package com.example.surya_shaktisolarmonitor.domain.usecase

import javax.inject.Inject

class CalculateSavingsUseCase @Inject constructor() {
    operator fun invoke(generated: Double, consumed: Double, rate: Double): SavingsResult {
        val netSolarUsed = if (generated >= consumed) consumed else generated
        val overGeneration = if (generated > consumed) generated - consumed else 0.0
        val savings = netSolarUsed * rate
        val exportCredit = overGeneration * rate // Assuming same rate for export for now
        
        return SavingsResult(
            savingsInInr = savings,
            overGenerationKwh = overGeneration,
            exportCreditInInr = exportCredit,
            isOverGenerating = generated > consumed
        )
    }
}

data class SavingsResult(
    val savingsInInr: Double,
    val overGenerationKwh: Double,
    val exportCreditInInr: Double,
    val isOverGenerating: Boolean
)
