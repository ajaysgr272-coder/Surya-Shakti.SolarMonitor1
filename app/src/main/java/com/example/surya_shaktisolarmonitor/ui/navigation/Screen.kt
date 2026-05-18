package com.example.surya_shaktisolarmonitor.ui.navigation

sealed class Screen(val route: String) {
    object ProfileSetup : Screen("profile_setup")
    object Home : Screen("home")
    object GenerationLog : Screen("generation_log")
    object ConsumptionTracker : Screen("consumption_tracker")
    object SavingsReport : Screen("savings_report")
    object History : Screen("history")
    object DistrictMap : Screen("district_map")
    object IllustratedStory : Screen("illustrated_story")
    object HeroQuiz : Screen("hero_quiz")
    object StatueFinder : Screen("statue_finder")
}
