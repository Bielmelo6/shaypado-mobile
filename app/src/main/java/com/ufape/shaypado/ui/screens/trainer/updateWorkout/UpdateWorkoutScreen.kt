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
import com.ufape.shaypado.ui.components.AppDropdown
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.DropdownItem
import com.ufape.shaypado.ui.components.EditButton
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
    var showEditExerciseDialog by rememberSaveable { mutableStateOf(false) }

    val updateWorkoutViewModel = hiltViewModel<UpdateWorkoutViewModel>()
    val workoutData by updateWorkoutViewModel.workoutData.collectAsState(
        initial = Result.Loading
    )

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        updateWorkoutViewModel.fetchWorkout(workoutId)
    }

    LaunchedEffect(key1 = updateWorkoutViewModel.workoutUpdateEvent) {
        updateWorkoutViewModel.workoutUpdateEvent.collect {
            if (it is Result.Success) {
                navController.popBackStack()
            } else if (it is Result.Error) {
                showSnackBar(it.exception.getErrorMessage(context))
            }
        }
    }

    if (workoutData is Result.Error) {
        AppText(
            text = (workoutData as Result.Error).exception.getErrorMessage(LocalContext.current),
        )
        return
    }

    if (workoutData is Result.Loading) {
        AppText(
            text = R.string.loading,
        )
        return
    }

    val categories = updateWorkoutViewModel.categoriesData.map {
        DropdownItem(
            it.category,
            it.id
        )
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
            value = updateWorkoutViewModel.workoutState.name,
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
                updateWorkoutViewModel.onWorkoutEvent(
                    TrainingsFormEvent.OnCategoryChanged(
                        category = it
                    )
                )
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
                                Row {
                                    EditButton(
                                        variant = ButtonVariant.TERTIARY,
                                        onClick = {
                                            updateWorkoutViewModel.setExerciseData(
                                                it
                                            )
                                            showEditExerciseDialog = true
                                        }
                                    )
                                }
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
            errorMessage = updateWorkoutViewModel.createExerciseData.titleError,
            value = updateWorkoutViewModel.createExerciseData.title,
            onValueChange = {
                updateWorkoutViewModel.onCreateExerciseEvent(ExerciseFormEvent.OnTitleChanged(it))
            },
            placeholder = R.string.exercise_title_placeholder,
        )

        Spacer(modifier = Modifier.height(16.dp))


        AppDropdown(
            items = categories,
            onItemSelected = { value, label ->
                updateWorkoutViewModel.onEditExerciseEvent(
                    ExerciseFormEvent.OnCategoryChanged(
                        id = value,
                        category = label
                    )
                )
            },
            label = "Categoria",
            selectedValue = updateWorkoutViewModel.editExerciseData.categoryId,
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = R.string.exercise_description,
            errorMessage = updateWorkoutViewModel.createExerciseData.descriptionError,
            value = updateWorkoutViewModel.createExerciseData.description,
            onValueChange = {
                updateWorkoutViewModel.onCreateExerciseEvent(
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
            errorMessage = updateWorkoutViewModel.createExerciseData.videoUrlError,
            value = updateWorkoutViewModel.createExerciseData.videoUrl ?: "",
            onValueChange = {
                updateWorkoutViewModel.onCreateExerciseEvent(
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
                    value = updateWorkoutViewModel.createExerciseData.series,
                    errorMessage = updateWorkoutViewModel.createExerciseData.seriesError,
                    onValueChange = {
                        updateWorkoutViewModel.onCreateExerciseEvent(
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
                    value = updateWorkoutViewModel.createExerciseData.repetitions,
                    errorMessage = updateWorkoutViewModel.createExerciseData.repetitionsError,
                    onValueChange = {
                        updateWorkoutViewModel.onCreateExerciseEvent(
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
                    time = updateWorkoutViewModel.createExerciseData.time,
                    label = R.string.time,
                    onConfirm = {
                        updateWorkoutViewModel.onCreateExerciseEvent(
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
            text = R.string.create_exercise,
            onClick = {
                updateWorkoutViewModel.onCreateExerciseEvent(ExerciseFormEvent.OnSubmit)
                showCreateExerciseDialog = false
            },
        )
    }

    AppDialog(
        onDismiss = {
            showEditExerciseDialog = false
        },
        isDialogVisible = showEditExerciseDialog
    ) {
        CustomTextField(
            label = R.string.exercise_title,

            value = updateWorkoutViewModel.editExerciseData.title,
            errorMessage = updateWorkoutViewModel.editExerciseData.titleError,
            onValueChange = {
                updateWorkoutViewModel.onEditExerciseEvent(ExerciseFormEvent.OnTitleChanged(it))
            },
            placeholder = R.string.exercise_title_placeholder,
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = R.string.exercise_description,
            value = updateWorkoutViewModel.editExerciseData.description,
            onValueChange = {
                updateWorkoutViewModel.onEditExerciseEvent(
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
            value = updateWorkoutViewModel.editExerciseData.videoUrl ?: "",
            errorMessage = updateWorkoutViewModel.editExerciseData.videoUrlError,
            onValueChange = {
                updateWorkoutViewModel.onEditExerciseEvent(
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
                    value = updateWorkoutViewModel.editExerciseData.series,
                    errorMessage = updateWorkoutViewModel.editExerciseData.seriesError,
                    onValueChange = {
                        updateWorkoutViewModel.onEditExerciseEvent(
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
                    value = updateWorkoutViewModel.editExerciseData.repetitions,
                    errorMessage = updateWorkoutViewModel.editExerciseData.repetitionsError,
                    onValueChange = {
                        updateWorkoutViewModel.onEditExerciseEvent(
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
                    time = updateWorkoutViewModel.editExerciseData.time,
                    label = R.string.time,
                    onConfirm = {
                        updateWorkoutViewModel.onEditExerciseEvent(
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
                showEditExerciseDialog = false
            },
            variant = ButtonVariant.SECONDARY_CONTAINER
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            variant = ButtonVariant.ERROR_CONTAINER,
            text = R.string.remove_exercise,
            onClick = {
                updateWorkoutViewModel.removeExercise()
                showEditExerciseDialog = false
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            text = R.string.update_exercise,
            onClick = {
                updateWorkoutViewModel.onEditExerciseEvent(ExerciseFormEvent.OnSubmit)
                showEditExerciseDialog = false
            },
        )
    }
}