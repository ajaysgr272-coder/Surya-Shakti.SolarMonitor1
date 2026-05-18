package com.example.surya_shaktisolarmonitor.data.repository

import com.google.ai.client.generativeai.GenerativeModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiRepository @Inject constructor(
    private val generativeModel: GenerativeModel
) {
    suspend fun getEnergyTip(generated: Double, consumed: Double, weather: String): String {
        val prompt = """
            Today's solar energy generation: $generated kWh.
            Household energy consumption: $consumed kWh.
            Weather condition: $weather.
            Status: ${if (generated > consumed) "Over-generation" else "Deficit"}.
            Provide 2-3 lines of personalized energy-saving tips for this Indian household.
        """.trimIndent()

        return try {
            val response = generativeModel.generateContent(prompt)
            response.text ?: "Could not get a tip at the moment. Try later!"
        } catch (e: Exception) {
            "Error: ${e.message}. Please check your internet connection and API key."
        }
    }
}
