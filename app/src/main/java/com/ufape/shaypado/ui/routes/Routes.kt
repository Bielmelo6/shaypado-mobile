package com.ufape.shaypado.ui.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ufape.shaypado.ui.model.UserType
import com.ufape.shaypado.ui.screens.login.AuthViewModel
import com.ufape.shaypado.ui.screens.signUp.SignUpViewModel

@Composable
fun Routes(authViewModel: AuthViewModel) {
    val signUpViewModel = hiltViewModel<SignUpViewModel>()

    val navController = rememberNavController()

    fun logout() {
        authViewModel.logout()
    }

    fun navigateToTrainer() {
        navController.navigate(TrainerNavigationScreen.NavRoot.route) {
            popUpTo(AuthNavigationScreen.NavRoot.route) {
                inclusive = true
            }
        }
    }

    fun navigateToUser() {
        navController.navigate(MobileNavigationScreen.NavRoot.route) {
            popUpTo(AuthNavigationScreen.NavRoot.route) {
                inclusive = true
            }
        }
    }

    fun navigateFromUserToRoot() {
        navController.navigate(AuthNavigationScreen.NavRoot.route) {
            popUpTo(MobileNavigationScreen.NavRoot.route) {
                inclusive = true
            }
        }
    }

    fun navigateFromTrainerToRoot() {
        navController.navigate(AuthNavigationScreen.NavRoot.route) {
            popUpTo(TrainerNavigationScreen.NavRoot.route) {
                inclusive = true
            }
        }
    }

    fun navigateToRoot() {
        navController.navigate(AuthNavigationScreen.NavRoot.route) {
            popUpTo(AuthNavigationScreen.NavRoot.route) {
                inclusive = true
            }
        }
    }

    //THis handles login, logout, email validation and user type
    LaunchedEffect(key1 = authViewModel.loggedInState) {
        authViewModel.loggedInState.collect {
            if (it.token?.isNotEmpty() == true) {
                if (!it.isEmailValid) {
                    navController.navigate(AuthNavigationScreen.SignUserCreated.route)
                } else {
                    when (it.userType) {
                        UserType.USER -> navigateToUser()

                        UserType.TRAINER -> navigateToTrainer()

                        else -> navigateToRoot()
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = authViewModel.sessionExpired) {
        authViewModel.sessionExpired.collect { didSessionExpire ->
            if (didSessionExpire) {
                authViewModel.loggedInState.collect {
                    when (it.userType) {
                        UserType.USER -> navigateFromUserToRoot()

                        UserType.TRAINER -> navigateFromTrainerToRoot()

                        else -> navigateToRoot()
                    }
                    authViewModel.logout()
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = AuthNavigationScreen.NavRoot.route
    ) {
        authNavGraph(
            navController = navController,
            signUpViewModel = signUpViewModel,
            authViewModel = authViewModel
        )

        composable(route = MobileNavigationScreen.NavRoot.route) {
            MobileRoutes {
                logout()
                navigateFromUserToRoot()
            }
        }

        composable(route = TrainerNavigationScreen.NavRoot.route) {
            TrainerRoutes {
                logout()
                navigateFromTrainerToRoot()
            }
        }
    }
}
