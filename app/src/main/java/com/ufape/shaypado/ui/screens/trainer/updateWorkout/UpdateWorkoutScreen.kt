package com.ufape.shaypado.ui.screens.trainer.updateWorkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AddButton
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppDialog
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.RemoveButton
import com.ufape.shaypado.ui.components.TimePicker
import com.ufape.shaypado.ui.screens.trainer.createTrainings.ExerciseFormEvent
import com.ufape.shaypado.ui.screens.trainer.createTrainings.TrainingsFormEvent
import com.ufape.shaypado.ui.screens.trainer.home.Dropdown
import com.ufape.shaypado.ui.screens.trainer.home.UserDetailsRenderItem
import com.ufape.shaypado.ui.theme.TrainingImage
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getErrorMessage

@Composable
fun UpdateWorkoutScreen(
    navController: NavController,
    showSnackBar: (String) -> Unit,
    workoutId: String
) {
    var dropdownExpanded by rememberSaveable { mutableStateOf(false) }
    var showCreateExerciseDialog by rememberSaveable { mutableStateOf(false) }

    val updateWorkoutViewModel = hiltViewModel<UpdateWorkoutViewModel>()
    val workoutData by updateWorkoutViewModel.workoutData.collectAsState(
        initial = Result.Loading
    )

    LaunchedEffect(Unit){
        updateWorkoutViewModel.fetchWorkout(workoutId)
    }

    if (workoutData is Result.Error) {
        AppText(
            text = R.string.unknown_error,
        )
        return
    }

    if (workoutData is Result.Loading) {
        AppText(
            text = R.string.loading,
        )
        return
    }

    AppHeader(
        navController = navController
    )

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier =
        Modifier
            .fillMaxHeight(0.8f)
    )
    {
        CustomTextField(
            label = R.string.training_name,
            value = updateWorkoutViewModel.workoutState.name ,
            onValueChange = {
                updateWorkoutViewModel.onWorkoutEvent(TrainingsFormEvent.OnNameChanged(it))
            },
            placeholder = R.string.training_name_placeholder,
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = R.string.training_category,
            value = updateWorkoutViewModel.workoutState.category,
            onValueChange = {
                updateWorkoutViewModel.onWorkoutEvent(TrainingsFormEvent.OnCategoryChanged(it))
            },
            placeholder = R.string.training_category_placeholder,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Dropdown(
                title = R.string.exercises,
                isExpanded = dropdownExpanded,
                toggle = { dropdownExpanded = dropdownExpanded.not() },
                endHeaderContent = {
                    AddButton(
                        onClick = {
                            showCreateExerciseDialog = true
                        }
                    )
                }
            ) {
                LazyColumn(
                    modifier = Modifier.height(800.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(updateWorkoutViewModel.workoutState.exercises.size) {
                        UserDetailsRenderItem(
                            name = updateWorkoutViewModel.workoutState.exercises[it].title,
                            description = updateWorkoutViewModel.workoutState.exercises[it].description,
                            leadingIcon = {
                                TrainingImage()
                            },
                            trailingIcon = {
                                RemoveButton(
                                    variant = ButtonVariant.ERROR_CONTAINER,
                                    onClick = {
                                        updateWorkoutViewModel.onExerciseEvent(
                                            ExerciseFormEvent.RemoveCurrentExercise(
                                                it
                                            )
                                        )
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        AppButton(
            text = R.string.cancel,
            onClick = {
                navController.popBackStack()
            },
            variant = ButtonVariant.SECONDARY_CONTAINER
        )

        AppButton(
            text = R.string.update,
            onClick = {
                updateWorkoutViewModel.onWorkoutEvent(TrainingsFormEvent.OnSubmit)
            },
        )
    }

    AppDialog(
        onDismiss = {
            showCreateExerciseDialog = false
        },
        isDialogVisible = showCreateExerciseDialog
    ) {
        CustomTextField(
            label = R.string.exercise_title,
            value = updateWorkoutViewModel.exerciseData.title,
            onValueChange = {
                updateWorkoutViewModel.onExerciseEvent(ExerciseFormEvent.OnTitleChanged(it))
            },
            placeholder = R.string.exercise_title_placeholder,
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = R.string.exercise_description,
            value = updateWorkoutViewModel.exerciseData.description,
            onValueChange = {
                updateWorkoutViewModel.onExerciseEvent(
                    ExerciseFormEvent.OnDescriptionChanged(
                        it
                    )
                )
            },
            placeholder = R.string.exercise_description_placeholder,
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = R.string.exercise_video_url,
            value = updateWorkoutViewModel.exerciseData.videoUrl,
            onValueChange = {
                updateWorkoutViewModel.onExerciseEvent(
                    ExerciseFormEvent.OnVideoUrlChanged(
                        it
                    )
                )
            },
            placeholder = R.string.exercise_video_url_placeholder,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Row(
                modifier = Modifier
                    .weight(1f)

            ) {
                CustomTextField(
                    label = R.string.series,
                    keyboardType = KeyboardType.Number,
                    value = updateWorkoutViewModel.exerciseData.series,
                    errorMessage = updateWorkoutViewModel.exerciseData.seriesError,
                    onValueChange = {
                        updateWorkoutViewModel.onExerciseEvent(
                            ExerciseFormEvent.OnSeriesChanged(
                                it
                            )
                        )
                    },
                    placeholder = R.string.series_placeholder,
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                modifier = Modifier
                    .weight(1f)

            ) {
                CustomTextField(
                    label = R.string.repetitions,
                    keyboardType = KeyboardType.Number,
                    value = updateWorkoutViewModel.exerciseData.repetitions,
                    errorMessage = updateWorkoutViewModel.exerciseData.repetitionsError,
                    onValueChange = {
                        updateWorkoutViewModel.onExerciseEvent(
                            ExerciseFormEvent.OnRepetitionsChanged(
                                it
                            )
                        )
                    },
                    placeholder = R.string.repetitions_placeholder,
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                modifier = Modifier
                    .weight(1f)

            ) {
                TimePicker(
                    time = updateWorkoutViewModel.exerciseData.time,
                    label = R.string.time,
                    onConfirm = {
                        updateWorkoutViewModel.onExerciseEvent(
                            ExerciseFormEvent.OnTimeChanged(
                                it
                            )
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            text = R.string.cancel,
            onClick = {
                showCreateExerciseDialog = false
            },
            variant = ButtonVariant.SECONDARY_CONTAINER
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            text = R.string.end,
            onClick = {
                updateWorkoutViewModel.onExerciseEvent(
                    ExerciseFormEvent.OnSubmit
                )
                showCreateExerciseDialog = false
            },
        )
    }
}