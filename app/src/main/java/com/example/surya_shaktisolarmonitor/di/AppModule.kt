package com.example.surya_shaktisolarmonitor.di

import android.content.Context
import androidx.room.Room
import com.example.surya_shaktisolarmonitor.data.local.EnergyDatabase
import com.example.surya_shaktisolarmonitor.data.local.dao.EnergyDao
import com.google.ai.client.generativeai.GenerativeModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): EnergyDatabase {
        return Room.databaseBuilder(
            context,
            EnergyDatabase::class.java,
            "surya_shakti_db"
        ).build()
    }

    @Provides
    fun provideEnergyDao(database: EnergyDatabase): EnergyDao {
        return database.energyDao()
    }

    @Provides
    @Singleton
    fun provideGenerativeModel(): GenerativeModel {
        // Replace with actual API Key if needed, but for now we'll just set up the model
        return GenerativeModel(
            modelName = "gemini-pro",
            apiKey = "YOUR_GEMINI_API_KEY" // User should provide this
        )
    }
}
