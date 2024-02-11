package com.ufape.shaypado.ui.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ufape.shaypado.ui.screens.login.LoginViewModel

@Composable
fun Routes(navController: NavHostController) {
    val authViewModel = hiltViewModel<LoginViewModel>()

    LaunchedEffect(key1 = authViewModel.loggedInState) {
        authViewModel.loggedInState.collect {
            if (it.isLogged) {
                navController.navigate(MobileNavigationScreen.NavRoot.route)
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = AuthNavigationScreen.NavRoot.route) {
        authNavGraph(navController = navController)
        composable(route = MobileNavigationScreen.NavRoot.route) {
            MobileRoutes()
        }
    }
}
