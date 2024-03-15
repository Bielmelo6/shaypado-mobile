package com.ufape.shaypado.ui.screens.user.exerciseDetails

import android.widget.Space
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.components.YoutubePlayer
import com.ufape.shaypado.ui.model.ExerciseState
import com.ufape.shaypado.ui.screens.shimmers.ErrorScreen
import com.ufape.shaypado.ui.screens.shimmers.TrainerHomeShimmer
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getErrorMessage

@Composable
fun ExerciseDetailsScreen(
    navController: NavController,
    showSnackBar : (String) -> Unit,
    exerciseId : String
) {
    val viewModel = hiltViewModel<UserExerciseDetailsScreen>()
    val context = LocalContext.current

    var exerciseData by remember {
        mutableStateOf<Result<ExerciseState>>(Result.Loading)
    }

    LaunchedEffect(Unit) {
        viewModel.fetchExerciseDetails(exerciseId)
    }

    LaunchedEffect(key1 = viewModel.exerciseData) {
        viewModel.exerciseData.collect {
            exerciseData = if (it is Result.Error) {
                showSnackBar(it.exception.getErrorMessage(context))
                it
            } else {
                it
            }
        }

    }

    if (exerciseData is Result.Loading) {
        return TrainerHomeShimmer()
    }

    if (exerciseData is Result.Error) {
        return ErrorScreen {
            viewModel.fetchExerciseDetails(exerciseId)
        }
    }

    val exercise = (exerciseData as Result.Success).data
    AppHeader(navController = navController)

    YoutubePlayer(exercise.videoUrl)

    Spacer(modifier = Modifier.height(16.dp))

    AppText(
        textType = TextType.HEADLINE_LARGE,
        text = exercise.title
    )

    AppText(
        textType = TextType.BODY_MEDIUM,
        text = exercise.series + " x " + exercise.repetitions
    )

    Spacer(modifier = Modifier.height(16.dp))

    AppText(
        textType = TextType.BODY_LARGE,
        text = exercise.description
    )
}