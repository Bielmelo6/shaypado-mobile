package com.ufape.shaypado.ui.screens.trainer.studentDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.BackButton
import com.ufape.shaypado.ui.components.EditButton
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.model.UserWithWorkoutState
import com.ufape.shaypado.ui.routes.TrainerNavigationScreen
import com.ufape.shaypado.ui.screens.shimmers.ErrorScreen
import com.ufape.shaypado.ui.screens.shimmers.TrainerHomeShimmer
import com.ufape.shaypado.ui.screens.trainer.home.TrainingDetailsRenderItem
import com.ufape.shaypado.ui.screens.trainer.home.UserDetailsRenderItem
import com.ufape.shaypado.ui.theme.StudentImage
import com.ufape.shaypado.ui.theme.TrainingImage
import com.ufape.shaypado.util.Result

@Composable
fun StudentDetailsScreen(
    navController: NavController,
    studentId: String
) {
    val context = LocalContext.current

    val viewModel = hiltViewModel<StudentDetailsViewModel>()

    var userData by remember {
        mutableStateOf<Result<UserWithWorkoutState>>(Result.Loading)
    }

    LaunchedEffect(Unit) {
        viewModel.fetchStudent(studentId)
    }

    LaunchedEffect(key1 = viewModel.studentData) {
        viewModel.studentData.collect {
            userData = it
        }
    }

    if (userData is Result.Error) {
        return ErrorScreen {
            viewModel.fetchStudent(studentId)
        }
    }

    if (userData is Result.Loading) {
        return TrainerHomeShimmer()
    }

    val data = (userData as Result.Success).data

    var currentTraining by remember {
        mutableIntStateOf(0)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton {
            navController.popBackStack()
        }

        AppText(
            textType = TextType.HEADLINE_MEDIUM,
            textAlignment = TextAlign.Center,
            text = R.string.student_details
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    UserDetailsRenderItem(
        name = data.user.name,
        trailingIcon = { StudentImage() },
        leadingIcon = {
            EditButton(
                onClick = {
                    navController.navigate(TrainerNavigationScreen.WorkoutSheet.shortName + "/$studentId")
                }
            )
        }
    )

    Spacer(modifier = Modifier.height(12.dp))


    if (data.workouts.isEmpty()) {
        return
    }

    TrainingDetailsRenderItem(
        leadingButtonDisabled = currentTraining == 0,
        trailingButtonDisabled = currentTraining == data.workouts.size - 1,
        onLeadingButtonPressed = {
            currentTraining -= 1
        },
        onTrailingButtonPressed = {
            currentTraining += 1
        },
        title = data.workouts[currentTraining].name,
        description = data.workouts[currentTraining].category
    )

    Spacer(modifier = Modifier.height(20.dp))


    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(data.workouts[currentTraining].exercises.size) {
            UserDetailsRenderItem(
                name = data.workouts[currentTraining].exercises[it].title,
                description = data.workouts[currentTraining].exercises[it].description,
                leadingIcon = { TrainingImage() }
            )
        }
    }

}