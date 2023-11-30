package com.ufape.shaypado.ui.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Routes(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AuthNavigationScreen.NavRoot.route) {
        authNavGraph(navController = navController)
        composable(route = MobileNavigationScreen.NavRoot.route) {
            MobileRoutes()
        }
    }
}
