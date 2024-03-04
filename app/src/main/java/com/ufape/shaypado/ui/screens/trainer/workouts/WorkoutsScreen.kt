package com.ufape.shaypado.ui.screens.trainer.workouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
import com.ufape.shaypado.ui.components.EditButton
import com.ufape.shaypado.ui.components.RemoveButton
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.TrainerNavigationScreen
import com.ufape.shaypado.ui.theme.BarbellIcon
import com.ufape.shaypado.util.Result

@Composable
fun WorkoutsScreen(
    navController: NavController,
    showSnackBar: (String) -> Unit
) {
    val workoutViewModel = hiltViewModel<WorkoutsViewModel>()
    var showDeleteDialog by remember { mutableStateOf(false) }

    val workoutsData by workoutViewModel.workoutsData.collectAsState(
        initial = Result.Loading
    )

    LaunchedEffect(Unit) {
        workoutViewModel.fetchWorkouts()
    }

    if (workoutsData is Result.Error) {
        showSnackBar("Error fetching workouts")
        return
    }

    if (workoutsData is Result.Loading) {
        AppText(
            text = "Loading workouts..."
        )
        return
    }

    val workouts = (workoutsData as Result.Success).data

    AppDialog(
        onDismiss = { showDeleteDialog = false },
        isDialogVisible = showDeleteDialog
    ) {

        AppText(
            textAlignment = TextAlign.Center,
            text = "Tem certeza de que deseja deletar este treino? \n Caso ele esteja associado a alguma turma seus alunso não poderão mais ver ele.",
            textType = TextType.TITLE_MEDIUM
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            text = R.string.cancel,
            onClick = {
                showDeleteDialog = false
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            text = R.string.remove,
            variant = ButtonVariant.ERROR_CONTAINER,
            onClick = {
                workoutViewModel.deleteWorkout()
            },
        )


    }

    AppHeader(
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

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier = Modifier
            .fillMaxHeight(0.7f)
    ) {
        LazyColumn {
            items(workouts.size) { index ->
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row {
                        Row(
                            modifier = Modifier
                                .size(50.dp)
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(50.dp)
                                )
                                .border(
                                    width = 2.dp,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    shape = RoundedCornerShape(50.dp)
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            BarbellIcon()
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            AppText(
                                text = workouts[index].name,
                                textType = TextType.TITLE_MEDIUM
                            )
                            Row {
                                AppText(
                                    text = workouts[index].name,
                                    textType = TextType.TITLE_MEDIUM
                                )
                                AppText(
                                    text = workouts[index].category,
                                    textType = TextType.LABEL_SMALL
                                )
                            }
                        }
                    }

                    Row {
                        EditButton(
                            variant = ButtonVariant.TERTIARY,
                            onClick = {
                                navController.navigate(
                                    TrainerNavigationScreen.EditWorkout.shortName + "/${workouts[index].id}"
                                )
                            }
                        )

                        RemoveButton(
                            variant = ButtonVariant.ERROR_CONTAINER,
                            onClick = {
                                workoutViewModel.selectWorkoutToRemove(workouts[index].id)
                                showDeleteDialog = true
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider()
            }
        }
    }
}