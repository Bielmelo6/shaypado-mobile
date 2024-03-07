package com.ufape.shaypado.ui.screens.trainer.classDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.BackButton
import com.ufape.shaypado.ui.components.EditButton
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.TrainerNavigationScreen
import com.ufape.shaypado.ui.screens.trainer.home.ClassDetailsRenderItem
import com.ufape.shaypado.ui.screens.trainer.home.Dropdown
import com.ufape.shaypado.ui.screens.trainer.home.UserDetailsRenderItem
import com.ufape.shaypado.ui.theme.StudentImage
import com.ufape.shaypado.ui.theme.TrainingImage
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getErrorMessage

@Composable
fun ClassDetailsScreen(
    navController: NavController,
    classId : String
) {
    var dropdownExpanded by rememberSaveable { mutableStateOf(false) }
    var usersDropdownExpanded by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    val viewModel = hiltViewModel<EditClassViewModel>()

    val fetchClassEvent by viewModel.classEvent.collectAsState(
        initial = Result.Loading
    )

    LaunchedEffect(Unit) {
        viewModel.fetchClassInfo(classId)
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

    val classInfo = viewModel.classInfo

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
            text = R.string.class_details
        )

        EditButton {
            navController.navigate(TrainerNavigationScreen.EditClass.route)
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
    ClassDetailsRenderItem(
        name = classInfo.name,
        description = classInfo.startTime + " - " + classInfo.endTime,
        students = classInfo.students.size
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
        ) {
            LazyColumn(
                modifier = Modifier.height(800.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(classInfo.workouts.size) {
                    UserDetailsRenderItem(
                        name = classInfo.workouts[it].title,
                        description = classInfo.workouts[it].category,
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
        ) {
            LazyColumn(
                modifier = Modifier.height(800.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(classInfo.students.size) {
                    UserDetailsRenderItem(
                        name = classInfo.students[it].name,
                        leadingIcon = {
                            StudentImage()
                        },
                        onPress = {
                            navController.navigate(TrainerNavigationScreen.StudentDetails.route + "/${classInfo.students[it].friendshipCode}")
                        },
                    )
                }
            }
        }
    }
}