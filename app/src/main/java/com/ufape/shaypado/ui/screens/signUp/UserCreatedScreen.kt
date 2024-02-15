package com.ufape.shaypado.ui.screens.signUp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.routes.AuthNavigationScreen

@Composable
fun UserCreatedScreen(
    navController: NavController
) {

    fun goBack() {
        navController.navigate(AuthNavigationScreen.Login.route) {
            popUpTo(AuthNavigationScreen.NavRoot.route) {
                inclusive = true
            }
        }
    }

    SignUpScreenBase(
        title = R.string.sign_up_successful_title,
        navController = navController,
        buttonText = R.string.sign_up_user_created_button,
        onButtonClicked = {
            goBack()
        },
        onBackButtonClicked = {
            goBack()
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AppText(
                text = R.string.sign_up_user_created_title,
            )
        }
    }

}