import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.model.WorkoutState
import com.ufape.shaypado.ui.routes.MobileNavigationScreen
import com.ufape.shaypado.ui.screens.shimmers.ErrorScreen
import com.ufape.shaypado.ui.screens.shimmers.TrainerHomeShimmer
import com.ufape.shaypado.ui.screens.trainer.home.UserDetailsRenderItem
import com.ufape.shaypado.ui.screens.user.userTraining.UserWorkoutViewModel
import com.ufape.shaypado.ui.theme.PerfilShaypado2Icon
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getErrorMessage

@Composable
fun UserStartWorkoutScreen(
    navController: NavController,
    showSnackbar: (String) -> Unit,
    workoutId: String
) {
    val viewModel = hiltViewModel<UserWorkoutViewModel>()
    val context = LocalContext.current

    var workoutData by remember {
        mutableStateOf<Result<WorkoutState>>(Result.Loading)
    }

    LaunchedEffect(Unit) {
        viewModel.fetchWorkout(workoutId)
    }

    LaunchedEffect(key1 = viewModel.workoutConclude) {
        viewModel.workoutConclude.collect {
            if (it is Result.Error) {
                showSnackbar(it.exception.getErrorMessage(context))
            } else if (it is Result.Success) {
                showSnackbar("Treino concluído com sucesso")
                navController.popBackStack()
            }
        }
    }

    LaunchedEffect(key1 = viewModel.exerciseUndo) {
        viewModel.exerciseUndo.collect {
            if (it is Result.Error) {
                showSnackbar(it.exception.getErrorMessage(context))
            } else if (it is Result.Success) {
                showSnackbar("Exercício desmarcado como concluído")
            }
        }
    }

    LaunchedEffect(key1 = viewModel.exerciseConclude) {
        viewModel.exerciseConclude.collect {
            if (it is Result.Error) {
                showSnackbar(it.exception.getErrorMessage(context))
            } else if (it is Result.Success) {
                showSnackbar("Exercício concluído com sucesso")
            }
        }
    }


    LaunchedEffect(key1 = viewModel.workoutData) {
        viewModel.workoutData.collect {
            workoutData = if (it is Result.Error) {
                showSnackbar(it.exception.getErrorMessage(context))
                it
            } else {
                it
            }
        }
    }

    if (workoutData is Result.Loading) {
        return TrainerHomeShimmer()
    }

    if (workoutData is Result.Error) {
        return ErrorScreen {
            viewModel.fetchWorkout(workoutId)

        }
    }

    val workout = (workoutData as Result.Success).data

    Column(
        modifier = Modifier.fillMaxHeight(0.9f),
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = 10.dp,
                        vertical = 14.dp
                    )
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    AppText(
                        text = workout.name,
                        textType = TextType.HEADLINE_SMALL,
                        textAlignment = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    AppText(
                        text = workout.category,
                        textType = TextType.TITLE_SMALL,
                        textAlignment = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Box(
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp)
                        .background(color = MaterialTheme.colorScheme.errorContainer)
                ) { PerfilShaypado2Icon() }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn {
            items(workout.exercises.size) { index ->
                val title = workout.exercises[index].title
                val description =
                    workout.exercises[index].series + " x " + workout.exercises[index].repetitions
                UserDetailsRenderItem(
                    name = title,
                    description = description,
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(
                                    3.dp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    shape = CircleShape
                                )
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_barbell),
                                contentDescription = "Barbell",
                            )
                        }
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (workout.exercises[index].isEndExercise) {
                                    viewModel.undoExercise(workout.exercises[index].id)
                                } else {
                                    viewModel.concludeExercise(workout.exercises[index].id)
                                }
                            },
                            modifier = Modifier
                                .height(52.dp)
                                .width(52.dp)
                        ) {
                            Icon(
                                tint = if (workout.exercises[index].isEndExercise) {
                                    MaterialTheme.colorScheme.onSurface
                                } else {
                                    MaterialTheme.colorScheme.primary
                                },
                                imageVector = Icons.Outlined.CheckCircle,
                                contentDescription = "check",
                                modifier = Modifier
                                    .height(52.dp)
                                    .width(52.dp)
                            )
                        }
                    },
                    onPress = {
                        navController.navigate(MobileNavigationScreen.ExerciseDetails.route)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }

    Spacer(modifier = Modifier.height(16.dp))
    AppButton(
        onClick = {
            viewModel.concludeWorkout(workoutId)
        },
        text = "Finalizar"
    )

}