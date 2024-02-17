package com.ufape.shaypado.ui.routes

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ufape.shaypado.ui.components.Camera
import com.ufape.shaypado.ui.screens.forgotPassword.ForgotPasswordScreen
import com.ufape.shaypado.ui.screens.login.LoginScreen
import com.ufape.shaypado.ui.screens.login.AuthViewModel
import com.ufape.shaypado.ui.screens.onBoard.OnBoardScreen
import com.ufape.shaypado.ui.screens.signUp.ChooseProfileScreen
import com.ufape.shaypado.ui.screens.signUp.PersonalFormEvent
import com.ufape.shaypado.ui.screens.signUp.PersonalFormScreen
import com.ufape.shaypado.ui.screens.signUp.PhysicalFormScreen
import com.ufape.shaypado.ui.screens.signUp.ProfileFormScreen
import com.ufape.shaypado.ui.screens.signUp.SignUpViewModel
import com.ufape.shaypado.ui.screens.signUp.UserCreatedScreen
import com.ufape.shaypado.ui.screens.signUp.UserFormScreen
import com.ufape.shaypado.util.compressImage

sealed class AuthNavigationScreen(val route: String) {
    data object NavRoot : AuthNavigationScreen("auth")
    data object Login : AuthNavigationScreen("login")
    data object ForgotPassword : AuthNavigationScreen("forgot_password")
    data object SignUpUserForm : AuthNavigationScreen("sign_up_user_form")
    data object SignUpChooseProfile : AuthNavigationScreen("sign_up_choose_profile")
    data object SignUpProfileForm : AuthNavigationScreen("sign_up_profile_form")
    data object SignUpPhysicalForm : AuthNavigationScreen("sign_up_physical_form")
    data object SignUpPersonalForm : AuthNavigationScreen("sign_up_personal_form")
    data object SignUserCreated : AuthNavigationScreen("sign_up_user_created")
    data object SignUpCamera : AuthNavigationScreen("sign_up_user_camera")
    data object OnBoard : AuthNavigationScreen("on_board")
}

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    signUpViewModel: SignUpViewModel,
    authViewModel: AuthViewModel
) {
    navigation(
        route = AuthNavigationScreen.NavRoot.route,
        startDestination = AuthNavigationScreen.OnBoard.route
    ) {
        composable(AuthNavigationScreen.OnBoard.route) {
            OnBoardScreen(navController)
        }
        composable(AuthNavigationScreen.Login.route) {
            LoginScreen(navController, authViewModel)
        }
        composable(AuthNavigationScreen.ForgotPassword.route) {
            ForgotPasswordScreen(navController)
        }

        //Sign up screens
        composable(AuthNavigationScreen.SignUpUserForm.route) {
            UserFormScreen(navController, signUpViewModel)
        }
        composable(AuthNavigationScreen.SignUpChooseProfile.route) {
            ChooseProfileScreen(navController, signUpViewModel)
        }
        composable(AuthNavigationScreen.SignUpProfileForm.route) {
            ProfileFormScreen(navController, signUpViewModel)
        }
        composable(AuthNavigationScreen.SignUpPhysicalForm.route) {
            PhysicalFormScreen(navController, signUpViewModel)
        }
        composable(AuthNavigationScreen.SignUpPersonalForm.route) {
            PersonalFormScreen(navController, signUpViewModel)
        }
        composable(AuthNavigationScreen.SignUserCreated.route) {
            UserCreatedScreen(navController)
        }
        composable(AuthNavigationScreen.SignUpCamera.route) {
            val context = LocalContext.current
            Camera(
                context = context,
                onPicture = {
                    val file = it.compressImage(context)
                    signUpViewModel.onPersonalDataEvent(
                        PersonalFormEvent.OnProfilePictureChanged(file?.absolutePath)
                    )
                    navController.popBackStack()
                },
                onBackButton = {
                    navController.popBackStack()
                }
            )
        }
    }
}

