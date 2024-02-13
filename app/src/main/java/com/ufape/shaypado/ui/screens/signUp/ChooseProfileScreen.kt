package com.ufape.shaypado.ui.screens.signUp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.Chip
import com.ufape.shaypado.ui.components.ChipSelectionGroup
import com.ufape.shaypado.ui.routes.AuthNavigationScreen

@Composable
fun ChooseProfileScreen(
    navController: NavController,
    viewModel: SignUpViewModel
) {

    SignUpScreenBase(
        title = R.string.sign_up_user_type_title,
        navController = navController
    ) {
        val chips = listOf(
            Chip(
                value = "0",
                icon = R.drawable.common_chip,
                label = R.string.sign_up_find_normal_usertype,
                onClick = {
                    navController.navigate(AuthNavigationScreen.SignUpProfileForm.route)
                    viewModel.onUserDataEvent(
                        UserAccountFormEvent.OnUserTypeChanged("student")
                    )
                }
            ),
            Chip(
                value = "1",
                icon = R.drawable.personal_chip,
                label = R.string.sign_up_find_professional_usertype,
                onClick = {
                    navController.navigate(AuthNavigationScreen.SignUpPersonalForm.route)
                    viewModel.onUserDataEvent(
                        UserAccountFormEvent.OnUserTypeChanged("teacher")
                    )
                }
            )
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ChipSelectionGroup(
                errorMessage = viewModel.userAccountDataState.workoutTypeError,
                selectedChip = viewModel.userAccountDataState.workoutType,
                chips = chips,
                onChipSelected = {
                },
            )
        }
    }
}