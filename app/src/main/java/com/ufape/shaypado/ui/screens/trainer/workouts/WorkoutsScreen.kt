package com.ufape.shaypado.ui.screens.trainer.workouts

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ufape.shaypado.ui.components.AddButton
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.routes.TrainerNavigationScreen

@Composable
fun WorkoutsScreen(
    navController: NavController,
    showSnackBar : (String) -> Unit
) {
    AppHeader (
        navController = navController,
        trailingContent = {
            AddButton(
                variant = ButtonVariant.SECONDARY_CONTAINER,
                onClick = {
                    navController.navigate(TrainerNavigationScreen.CreateWorkout.route)
                }
            )
        }
    )
}