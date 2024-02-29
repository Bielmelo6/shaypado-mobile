package com.ufape.shaypado.ui.screens.trainer.createTrainings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.ufape.shaypado.ui.screens.trainer.counter.CounterBase
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AddButton
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.BackButton
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.NextButton
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.components.TimePicker
import com.ufape.shaypado.ui.screens.trainer.createClass.ClassFormEvent
import com.ufape.shaypado.ui.screens.trainer.home.Dropdown
import com.ufape.shaypado.ui.screens.trainer.home.UserDetailsRenderItem
import com.ufape.shaypado.ui.theme.TrainingImage

@Composable
fun CreateTrainingsScreen(
    navController: NavController,
) {
    var shouldShowForm by remember { mutableStateOf(false) }
    var dropdownExpanded by rememberSaveable { mutableStateOf(false) }

    var showPicker by rememberSaveable { mutableStateOf(false) }


    val createTrainingsViewModel = hiltViewModel<CreateTrainingsViewModel>()

    if (!shouldShowForm) {
        CounterBase(
            navController = navController,
            title = "Quantos alunos você deseja cadastrar?",
            value = createTrainingsViewModel.numberOfTrainings + 1,
            decrease = {
                createTrainingsViewModel.decreaseTrainings()
            },
            increase = {
                createTrainingsViewModel.increaseTrainings()
            },
            onNextPressed = {
                createTrainingsViewModel.allocateTrainings()
                shouldShowForm = true
            }
        )
        return
    }

    BackHandler {
        shouldShowForm = false
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(
            variant = ButtonVariant.PRIMARY,
            enabled = createTrainingsViewModel.selectedTraining != 0
        ) {
            createTrainingsViewModel.decreaseSelectedTraining()
        }

        AppText(
            text = String.format("%02d", createTrainingsViewModel.selectedTraining + 1),
            textAlignment = TextAlign.Center,
            textType = TextType.DISPLAY_SMALL
        )

        NextButton(
            variant = ButtonVariant.PRIMARY,
            enabled = createTrainingsViewModel.selectedTraining != createTrainingsViewModel.numberOfTrainings - 1
        ) {
            createTrainingsViewModel.increaseSelectedTraining()
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier =
        Modifier
            .fillMaxHeight(0.68f)
    )
    {
        CustomTextField(
            label = R.string.training_name,
            value = createTrainingsViewModel.trainingsData[createTrainingsViewModel.selectedTraining].name,
            onValueChange = {
                createTrainingsViewModel.onTrainingDataEvent(TrainingsFormEvent.OnNameChanged(it))
            },
            placeholder = R.string.training_name_placeholder,
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = R.string.training_category,
            value = createTrainingsViewModel.trainingsData[createTrainingsViewModel.selectedTraining].category,
            onValueChange = {
                createTrainingsViewModel.onTrainingDataEvent(TrainingsFormEvent.OnCategoryChanged(it))
            },
            placeholder = R.string.training_category_placeholder,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Dropdown(
                title = R.string.trainings,
                isExpanded = dropdownExpanded,
                toggle = { dropdownExpanded = dropdownExpanded.not() },
                endHeaderContent = {
                    AddButton(
                        onClick = {
                            showPicker = true
                        }
                    )
                }
            ) {
                LazyColumn(
                    modifier = Modifier.height(800.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(20) {
                        UserDetailsRenderItem(
                            leadingIcon = {
                                TrainingImage()
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
            text = R.string.delete_training,
            onClick = {
                createTrainingsViewModel.onTrainingDataEvent(TrainingsFormEvent.RemoveCurrentTraining)
            },
            variant = ButtonVariant.ERROR_CONTAINER
        )

        AppButton(
            text = R.string.cancel,
            onClick = {
                navController.popBackStack()
            },
            variant = ButtonVariant.SECONDARY_CONTAINER
        )

        AppButton(
            text = R.string.end,
            onClick = {

            },
        )
    }

    if (!showPicker) return

    Dialog(
        onDismissRequest = { showPicker = false },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 5.dp,
            modifier = Modifier
                .padding(16.dp)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextField(
                    label = R.string.exercise_title,
                    value = createTrainingsViewModel.exerciseData.title,
                    onValueChange = {
                        createTrainingsViewModel.onExerciseEvent(ExerciseFormEvent.OnTitleChanged(it))
                    },
                    placeholder = R.string.exercise_title_placeholder,
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    label = R.string.exercise_category,
                    value = createTrainingsViewModel.exerciseData.category,
                    onValueChange = {
                        createTrainingsViewModel.onExerciseEvent(ExerciseFormEvent.OnCategoryChanged(it))
                    },
                    placeholder = R.string.exercise_category_placeholder,
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    label = R.string.exercise_description,
                    value = createTrainingsViewModel.exerciseData.description,
                    onValueChange = {
                        createTrainingsViewModel.onExerciseEvent(ExerciseFormEvent.OnDescriptionChanged(it))
                    },
                    placeholder = R.string.exercise_description_placeholder,
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    label = R.string.exercise_video_url,
                    value = createTrainingsViewModel.exerciseData.videoUrl,
                    onValueChange = {
                        createTrainingsViewModel.onExerciseEvent(ExerciseFormEvent.OnVideoUrlChanged(it))
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
                            value = createTrainingsViewModel.exerciseData.videoUrl,
                            onValueChange = {
                                createTrainingsViewModel.onExerciseEvent(ExerciseFormEvent.OnVideoUrlChanged(it))
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
                            value = createTrainingsViewModel.exerciseData.videoUrl,
                            onValueChange = {
                                createTrainingsViewModel.onExerciseEvent(ExerciseFormEvent.OnVideoUrlChanged(it))
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
                            time = createTrainingsViewModel.exerciseData.time,
                            label = R.string.time,
                            onConfirm = {
                                createTrainingsViewModel.onExerciseEvent(ExerciseFormEvent.OnTimeChanged(it))
                            }
                        )
                    }



                }

                Spacer(modifier = Modifier.height(16.dp))

                AppButton(
                    text = R.string.cancel,
                    onClick = {
                        navController.popBackStack()
                    },
                    variant = ButtonVariant.SECONDARY_CONTAINER
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppButton(
                    text = R.string.end,
                    onClick = {

                    },
                )

            }
        }
    }
}