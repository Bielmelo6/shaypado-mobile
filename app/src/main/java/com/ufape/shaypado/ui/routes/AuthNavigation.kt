package com.ufape.shaypado.ui.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ufape.shaypado.ui.screens.forgotPassword.ForgotPasswordScreen
import com.ufape.shaypado.ui.screens.login.LoginScreen
import com.ufape.shaypado.ui.screens.onBoard.OnBoardScreen
import com.ufape.shaypado.ui.screens.signUp.ChooseProfileScreen
import com.ufape.shaypado.ui.screens.signUp.PersonalFormScreen
import com.ufape.shaypado.ui.screens.signUp.PhysicalFormScreen
import com.ufape.shaypado.ui.screens.signUp.ProfileFormScreen
import com.ufape.shaypado.ui.screens.signUp.UserFormScreen

sealed class AuthNavigationScreen(val route: String) {
    data object NavRoot : AuthNavigationScreen("auth")
    data object Login : AuthNavigationScreen("login")
    data object ForgotPassword : AuthNavigationScreen("forgot_password")
    data object SignUpUserForm : AuthNavigationScreen("sign_up_user_form")
    data object SignUpChooseProfile : AuthNavigationScreen("sign_up_choose_profile")
    data object SignUpProfileForm : AuthNavigationScreen("sign_up_profile_form")
    data object SignUpPhysicalForm : AuthNavigationScreen("sign_up_physical_form")
    data object SignUpPersonalForm : AuthNavigationScreen("sign_up_personal_form")
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

        //Sign up screens
        composable(AuthNavigationScreen.SignUpUserForm.route) {
            UserFormScreen(navController)
        }
        composable(AuthNavigationScreen.SignUpChooseProfile.route) {
            ChooseProfileScreen(navController)
        }
        composable(AuthNavigationScreen.SignUpProfileForm.route) {
            ProfileFormScreen(navController)
        }
        composable(AuthNavigationScreen.SignUpPhysicalForm.route) {
            PhysicalFormScreen(navController)
        }
        composable(AuthNavigationScreen.SignUpPersonalForm.route) {
            PersonalFormScreen(navController)
        }
    }
}

