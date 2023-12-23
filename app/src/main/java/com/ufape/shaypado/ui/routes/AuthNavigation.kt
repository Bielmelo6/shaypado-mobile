package com.ufape.shaypado.ui.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ufape.shaypado.ui.screens.forgotPassword.ForgotPasswordScreen
import com.ufape.shaypado.ui.screens.login.LoginScreen
import com.ufape.shaypado.ui.screens.onBoard.OnBoardScreen
import com.ufape.shaypado.ui.screens.signUp.SignUpScreen

sealed class AuthNavigationScreen(val route: String) {
    data object NavRoot : AuthNavigationScreen("auth")
    data object Login : AuthNavigationScreen("login")
    data object ForgotPassword : AuthNavigationScreen("forgot_password")
    data object SignUp : AuthNavigationScreen("sign_up")
    data object OnBoard : AuthNavigationScreen("on_board")
}

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = AuthNavigationScreen.NavRoot.route,
        startDestination = AuthNavigationScreen.OnBoard.route
    ) {
        composable(AuthNavigationScreen.OnBoard.route) {
            OnBoardScreen(navController)
        }
        composable(AuthNavigationScreen.Login.route) {
            LoginScreen(navController)
        }
        composable(AuthNavigationScreen.ForgotPassword.route) {
            ForgotPasswordScreen(navController)
        }
        composable(AuthNavigationScreen.SignUp.route) {
            SignUpScreen(navController)
        }
    }

}

