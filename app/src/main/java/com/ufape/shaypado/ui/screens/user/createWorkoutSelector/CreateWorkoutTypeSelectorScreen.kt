package com.ufape.shaypado.ui.screens.user.createWorkoutSelector

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.Chip
import com.ufape.shaypado.ui.components.ChipSelectionGroup
import com.ufape.shaypado.ui.routes.MobileNavigationScreen

@Composable
fun CreateWorkoutTypeSelectorScreen(
    navController: NavController
) {
    AppHeader(navController = navController, title = R.string.select_training_type)

    val chips = listOf(
        Chip(
            value = "0",
            icon = R.drawable.ic_workout_me,
            label = R.string.sign_up_yourself,
            onClick = {
                navController.navigate(MobileNavigationScreen.CreateWorkouts.route)
            }
        ),
        Chip(
            value = "1",
            icon = R.drawable.ic_workout_made,
            label = R.string.sign_up_saved_workout
        ),
        Chip(
            value = "2",
            icon = R.drawable.ic_workout_professional,
            label = R.string.sign_up_find_professional,
            onClick = {
                navController.navigate(MobileNavigationScreen.UserPersonalList.route)
            }
        )

    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ChipSelectionGroup(
            chips = chips,
            onChipSelected = {},
        )
    }
}