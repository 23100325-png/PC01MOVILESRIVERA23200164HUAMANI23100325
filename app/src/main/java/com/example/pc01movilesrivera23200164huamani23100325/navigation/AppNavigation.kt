package com.example.pc01movilesrivera23200164huamani23100325.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pc01movilesrivera23200164huamani23100325.ui.screens.BudgetPlannerScreen
import com.example.pc01movilesrivera23200164huamani23100325.ui.screens.LuggageCalculatorScreen
import com.example.pc01movilesrivera23200164huamani23100325.ui.screens.MenuScreen
import com.example.pc01movilesrivera23200164huamani23100325.ui.screens.PlaceholderScreen

sealed class Screen(val route: String) {
    object Menu : Screen("menu")
    object Luggage : Screen("luggage")
    object Budget : Screen("budget")
    object Catalog : Screen("catalog")
    object Permission : Screen("permission")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Menu.route) {
        composable(Screen.Menu.route) {
            MenuScreen(navController)
        }
        composable(Screen.Luggage.route) {
            LuggageCalculatorScreen(navController)
        }
        composable(Screen.Budget.route) {
            BudgetPlannerScreen(navController)
        }
        composable(Screen.Catalog.route) {
            PlaceholderScreen(navController, "Catálogo de Destinos Turísticos")
        }
        composable(Screen.Permission.route) {
            PlaceholderScreen(navController, "Permiso de Ubicación para Asistencia de Viaje")
        }
    }
}
