package com.max.ai.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.max.ai.ui.screens.HomeScreen
import com.max.ai.ui.screens.SettingsScreen
import com.max.ai.ui.screens.NotesScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Settings : Screen("settings")
    object Notes : Screen("notes")
}

@Composable
fun MaxNavGraph(onStartService: () -> Unit, onStartOverlay: () -> Unit) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) },
                onNavigateToNotes = { navController.navigate(Screen.Notes.route) }
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Notes.route) {
            NotesScreen(onBack = { navController.popBackStack() })
        }
    }
}
