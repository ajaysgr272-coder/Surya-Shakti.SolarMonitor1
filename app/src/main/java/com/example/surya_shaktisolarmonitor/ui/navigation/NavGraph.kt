package com.example.surya_shaktisolarmonitor.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.surya_shaktisolarmonitor.ui.screens.*
import com.example.surya_shaktisolarmonitor.ui.screens.heritage.*
import com.example.surya_shaktisolarmonitor.ui.viewmodel.MainViewModel

@Composable
fun NavGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            DashboardScreen(navController, viewModel)
        }

        composable(Screen.ProfileSetup.route) {
            ProfileSetupScreen(navController, viewModel)
        }

        composable(Screen.GenerationLog.route) {
            GenerationLogScreen(navController, viewModel)
        }

        composable("${Screen.ConsumptionTracker.route}/{generated}/{weather}") { backStackEntry ->
            val generated = backStackEntry.arguments?.getString("generated")?.toDoubleOrNull() ?: 0.0
            val weather = backStackEntry.arguments?.getString("weather") ?: "Sunny"
            ConsumptionTrackerScreen(navController, viewModel, generated, weather)
        }

        composable(Screen.History.route) {
            HistoryScreen(navController, viewModel)
        }

        composable(Screen.SavingsReport.route) {
            SavingsReportScreen(navController, viewModel)
        }

        // Heritage Module Routes
        composable(Screen.DistrictMap.route) { DistrictMapScreen(navController) }
        composable(Screen.IllustratedStory.route) { IllustratedStoryScreen(navController) }
        composable(Screen.HeroQuiz.route) { HeroQuizScreen(navController) }
        composable(Screen.StatueFinder.route) { StatueFinderScreen(navController) }
    }
}
