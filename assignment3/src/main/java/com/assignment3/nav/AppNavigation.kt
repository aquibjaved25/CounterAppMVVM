package com.assignment3.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.assignment3.DatabaseMainScreen
import com.assignment3.DetailsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavDestination.Home.route
    ) {
        composable(
            route = NavDestination.Home.route
        ) {
            DatabaseMainScreen(navController)
        }

        composable<NavDestination.TypeSafeDestination> { backStackEntry ->
            val destination =
                backStackEntry.savedStateHandle.toRoute<NavDestination.TypeSafeDestination>()
            DetailsScreen(navController, destination)
        }
    }
}