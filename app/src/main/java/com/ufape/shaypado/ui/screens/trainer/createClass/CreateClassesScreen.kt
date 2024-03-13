package com.ufape.shaypado.ui.screens.trainer.createClass

import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AddButton
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.BackButton
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.DaysOfWeekChooser
import com.ufape.shaypado.ui.components.NextButton
import com.ufape.shaypado.ui.components.RemoveButton
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.components.TimePicker
import com.ufape.shaypado.ui.routes.TrainerNavigationScreen
import com.ufape.shaypado.ui.screens.trainer.counter.CounterBase
import com.ufape.shaypado.ui.screens.trainer.createTrainings.TrainingsFormEvent
import com.ufape.shaypado.ui.screens.trainer.home.Dropdown
import com.ufape.shaypado.ui.screens.trainer.home.UserDetailsRenderItem
import com.ufape.shaypado.ui.theme.StudentImage
import com.ufape.shaypado.ui.theme.TrainingImage
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getErrorMessage

@Composable
fun CreateClassesScreen(
    navController: NavController,
    showSnackbar : (String) -> Unit,
    createClassViewModel: CreateClassViewModel
) {
    var shouldShowForm by rememberSaveable { mutableStateOf(false) }
    var dropdownExpanded by rememberSaveable { mutableStateOf(false) }
    var usersDropdownExpanded by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    if (!shouldShowForm) {
        CounterBase(
            navController = navController,
            title = "Quantos turmas diferentes você deseja cadastrar?",
            value = createClassViewModel.numberOfClasses + 1,
            decrease = {
                createClassViewModel.decreaseClasses()
            },
            increase = {
                createClassViewModel.increaseClasses()
            },
            onNextPressed = {
                createClassViewModel.allocateClasses()
                shouldShowForm = true
            }
        )
        return
    }

    BackHandler {
        shouldShowForm = false
    }

    LaunchedEffect(key1 = createClassViewModel.createClassesEvent) {
        createClassViewModel.createClassesEvent.collect{
            if (it is Result.Success) {
                showSnackbar("Turmas criadas com sucesso")
                navController.popBackStack()
            }else if (it is Result.Error) {
                showSnackbar(it.exception.getErrorMessage(context))
            }
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(
            variant = ButtonVariant.PRIMARY,
            enabled = createClassViewModel.selectedClass != 0
        ) {
            createClassViewModel.decreaseSelectedClass()
        }

        AppText(
            text = String.format("%02d", createClassViewModel.selectedClass + 1),
            textAlignment = TextAlign.Center,
            textType = TextType.DISPLAY_SMALL
        )

        NextButton(
            variant = ButtonVariant.PRIMARY,
            enabled = createClassViewModel.selectedClass != createClassViewModel.numberOfClasses - 1
        ) {
            createClassViewModel.increaseSelectedClass()
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
            label = R.string.class_name,
            value = createClassViewModel.classData[createClassViewModel.selectedClass].name,
            onValueChange = {
                createClassViewModel.onClassDataEvent(ClassFormEvent.OnNameChanged(it))
            },
            placeholder = R.string.class_name_placeholder,
        )

        Spacer(modifier = Modifier.height(16.dp))

        DaysOfWeekChooser(
            itemsSelected = createClassViewModel.classData[createClassViewModel.selectedClass].daysOfWeek,
            onItemSelected = {
                createClassViewModel.onClassDataEvent(ClassFormEvent.OnDaysOfWeekChanged(it))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Row(
                modifier = Modifier
                    .weight(1f)

            ) {
                TimePicker(
                    time = createClassViewModel.classData[createClassViewModel.selectedClass].startTime,
                    label = R.string.start_time,
                    onConfirm = {
                        createClassViewModel.onClassDataEvent(ClassFormEvent.OnStartingTimeChanged(it))
                    }
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                modifier = Modifier
                    .weight(1f)

            ) {
                TimePicker(
                    time = createClassViewModel.classData[createClassViewModel.selectedClass].endTime,
                    label = R.string.end_time,
                    onConfirm = {
                        createClassViewModel.onClassDataEvent(ClassFormEvent.OnEndingTimeChanged(it))
                    }
                )
            }
        }

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
                            navController.navigate(TrainerNavigationScreen.ImportWorkouts.route)
                            shouldShowForm = true
                        }
                    )
                }
            ) {
                LazyColumn(
                    modifier = Modifier.height(800.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(createClassViewModel.classData[createClassViewModel.selectedClass].workouts.size) {
                        UserDetailsRenderItem(
                            name = createClassViewModel.classData[createClassViewModel.selectedClass].workouts[it].title,
                            description = createClassViewModel.classData[createClassViewModel.selectedClass].workouts[it].category,
                            leadingIcon = {
                                TrainingImage()
                            },
                            trailingIcon = {
                                RemoveButton(
                                    variant = ButtonVariant.ERROR_CONTAINER,
                                    onClick = {
                                        createClassViewModel.removeWorkout(it)
                                    }
                                )
                            }
                        )
                    }
                }
            }

            Dropdown(
                title = R.string.stundets,
                isExpanded = usersDropdownExpanded,
                toggle = { usersDropdownExpanded = usersDropdownExpanded.not() },
                endHeaderContent = {
                    AddButton(
                        onClick = {
                            navController.navigate(TrainerNavigationScreen.ImportFriends.route)
                            shouldShowForm = true                        }
                    )
                }
            ) {
                LazyColumn(
                    modifier = Modifier.height(800.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(createClassViewModel.classData[createClassViewModel.selectedClass].students.size) {
                        UserDetailsRenderItem(
                            name = createClassViewModel.classData[createClassViewModel.selectedClass].students[it].name,
                            description = "",
                            leadingIcon = {
                                StudentImage()
                            },
                            trailingIcon = {
                                RemoveButton(
                                    variant = ButtonVariant.ERROR_CONTAINER,
                                    onClick = {
                                        createClassViewModel.removeFriend(it)
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
            text = R.string.delete_class,
            onClick = {
                createClassViewModel.onClassDataEvent(ClassFormEvent.RemoveCurrentClass)
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
                      createClassViewModel.onClassDataEvent(ClassFormEvent.OnSubmit)
            },
        )
    }

}