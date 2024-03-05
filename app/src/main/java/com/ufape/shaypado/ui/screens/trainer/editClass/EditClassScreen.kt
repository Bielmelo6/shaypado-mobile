package com.ufape.shaypado.ui.screens.trainer.editClass

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AddButton
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.BackButton
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.DaysOfWeekChooser
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.components.TimePicker
import com.ufape.shaypado.ui.routes.TrainerNavigationScreen
import com.ufape.shaypado.ui.screens.trainer.classDetails.EditClassViewModel
import com.ufape.shaypado.ui.screens.trainer.createClass.ClassFormEvent
import com.ufape.shaypado.ui.screens.trainer.home.Dropdown
import com.ufape.shaypado.ui.screens.trainer.home.UserDetailsRenderItem
import com.ufape.shaypado.ui.theme.StudentImage
import com.ufape.shaypado.ui.theme.TrainingImage
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getErrorMessage

@Composable
fun EditClassScreen(
    navController: NavController,
    classId: String,
    editClassViewModel: EditClassViewModel
) {
    var dropdownExpanded by rememberSaveable { mutableStateOf(false) }
    var usersDropdownExpanded by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    val fetchClassEvent by editClassViewModel.classEvent.collectAsState(
        initial = Result.Loading
    )

    LaunchedEffect(Unit) {
        editClassViewModel.fetchClassInfo(classId)
    }

    if (fetchClassEvent is Result.Error) {
        AppText(
            text = (fetchClassEvent as Result.Error).exception.getErrorMessage(context),
        )
    }

    if (fetchClassEvent is Result.Loading) {
        AppText(
            text = R.string.loading
        )

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
            text = R.string.edit_class,
            fillWidth = true
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier =
        Modifier
            .fillMaxHeight(0.8f)
    )
    {
        CustomTextField(
            label = R.string.class_name,
            value = editClassViewModel.classInfo.name,
            onValueChange = {
                editClassViewModel.onClassEvent(ClassFormEvent.OnNameChanged(it))
            },
            placeholder = R.string.class_name_placeholder,
        )

        Spacer(modifier = Modifier.height(16.dp))

        DaysOfWeekChooser(
            onItemSelected = {
                editClassViewModel.onClassEvent(ClassFormEvent.OnDaysOfWeekChanged(it))
            },
            itemsSelected = editClassViewModel.classInfo.daysOfWeek
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Row(
                modifier = Modifier
                    .weight(1f)

            ) {
                TimePicker(
                    time = editClassViewModel.classInfo.startTime,
                    onConfirm = {
                        editClassViewModel.onClassEvent(ClassFormEvent.OnStartingTimeChanged(it))
                    },
                    label = R.string.start_time
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                modifier = Modifier
                    .weight(1f)

            ) {
                TimePicker(
                    time = editClassViewModel.classInfo.endTime,
                    onConfirm = {
                        editClassViewModel.onClassEvent(ClassFormEvent.OnEndingTimeChanged(it))
                    },
                    label = R.string.end_time
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
                            navController.navigate(TrainerNavigationScreen.ImportFromUpdateWorkouts.route)
                        }
                    )
                }
            ) {
                LazyColumn(
                    modifier = Modifier.height(800.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(editClassViewModel.classInfo.workouts.size) {
                        UserDetailsRenderItem(
                            name = editClassViewModel.classInfo.workouts[it].title,
                            description = editClassViewModel.classInfo.workouts[it].category,
                            leadingIcon = {
                                TrainingImage()
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Dropdown(
                title = R.string.stundets,
                isExpanded = usersDropdownExpanded,
                toggle = { usersDropdownExpanded = usersDropdownExpanded.not() },
                endHeaderContent = {
                    AddButton(
                        onClick = {
                            navController.navigate(TrainerNavigationScreen.ImportFromUpdateFriends.route)
                        }
                    )
                }
            ) {
                LazyColumn(
                    modifier = Modifier.height(800.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(editClassViewModel.classInfo.students.size) {
                        UserDetailsRenderItem(
                            name = editClassViewModel.classInfo.students[it].name,
                            leadingIcon = {
                                StudentImage()
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
            onClick = { },
            variant = ButtonVariant.ERROR_CONTAINER
        )

        AppButton(
            text = R.string.save,
            onClick = { },
        )
    }
}