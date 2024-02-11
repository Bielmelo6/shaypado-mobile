package com.ufape.shaypado.ui.screens.signUp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.Chip
import com.ufape.shaypado.ui.components.ChipSelectionGroup

@Composable
fun ChooseTrainingStyle(
    navController: NavController
) {
    val viewModel = hiltViewModel<SignUpViewModel>()

    SignUpScreenBase(
        title = R.string.sign_up_title,
        buttonText = R.string.button_next,
        onButtonClicked = {
            viewModel.onUserDataEvent(UserAccountFormEvent.OnSubmit)
        }
    ) {
        val chips = listOf(
            Chip(
                value = "0",
                icon = R.drawable.common_chip,
                label = R.string.sign_up_yourself
            ),
            Chip(
                value = "1",
                icon = R.drawable.common_chip,
                label = R.string.sign_up_saved_workout
            ),
            Chip(
                value = "2",
                icon = R.drawable.common_chip,
                label = R.string.sign_up_find_professional
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
