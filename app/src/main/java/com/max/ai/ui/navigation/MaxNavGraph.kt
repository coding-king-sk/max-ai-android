package com.max.ai.ui.navigation
import androidx.compose.runtime.Composable; import androidx.navigation.compose.NavHost; import androidx.navigation.compose.composable; import androidx.navigation.compose.rememberNavController
import com.max.ai.ui.screens.HomeScreen; import com.max.ai.ui.screens.SettingsScreen; import com.max.ai.ui.screens.NotesScreen
sealed class Screen(val route: String) { object Home : Screen("home"); object Settings : Screen("settings"); object Notes : Screen("notes") }
@Composable fun MaxNavGraph(onSvc: () -> Unit) {
    val nav = rememberNavController()
    NavHost(nav, Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen({ nav.navigate(Screen.Settings.route) }, { nav.navigate(Screen.Notes.route) }) }
        composable(Screen.Settings.route) { SettingsScreen({ nav.popBackStack() }) }
        composable(Screen.Notes.route) { NotesScreen({ nav.popBackStack() }) }
    }
}
