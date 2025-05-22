package com.example.practice.navigation

sealed class Screen(val route: String) {
    data object Dashboard: Screen("dashboard")
    data object Profile: Screen("profile")
    data object ScoreLogic: Screen("scoreLogic")
}