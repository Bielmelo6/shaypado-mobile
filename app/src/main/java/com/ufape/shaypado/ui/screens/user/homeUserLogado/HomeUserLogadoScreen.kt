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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AddButton
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.EditButton
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.model.WorkoutState
import com.ufape.shaypado.ui.routes.MobileNavigationScreen
import com.ufape.shaypado.ui.screens.shimmers.ErrorScreen
import com.ufape.shaypado.ui.screens.shimmers.TrainerHomeShimmer
import com.ufape.shaypado.ui.screens.trainer.home.ExerciseDetailsRenderItemPreview
import com.ufape.shaypado.ui.screens.trainer.home.TrainingDetailsRenderItem
import com.ufape.shaypado.ui.screens.trainer.home.UserDetailsRenderItem
import com.ufape.shaypado.ui.screens.user.homeUserLogado.HomeUserViewModel
import com.ufape.shaypado.ui.theme.PerfilShaypado2Icon
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getErrorMessage


@Composable
fun HomeUserLogadoScreen(
    navController: NavController,
    showSnackbar: (String) -> Unit
) {
    val viewModel = hiltViewModel<HomeUserViewModel>()
    val context = LocalContext.current

    var workoutsData by remember { mutableStateOf<Result<List<WorkoutState>>>(Result.Loading) }

    LaunchedEffect(Unit) {
        viewModel.fetchWorkouts()
    }

    LaunchedEffect(key1 = viewModel.workoutsData) {
        viewModel.workoutsData.collect {
            workoutsData = if (it is Result.Error) {
                showSnackbar(it.exception.getErrorMessage(context))
                it
            } else {
                it
            }
        }
    }

    if (workoutsData is Result.Loading) {
        return TrainerHomeShimmer()
    }

    if (workoutsData is Result.Error) {
        return ErrorScreen {
            viewModel.fetchWorkouts()
        }
    }

    val workouts = (workoutsData as Result.Success).data
    var selectedWorkout by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxHeight(0.9f)

    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            EditButton(variant = ButtonVariant.SECONDARY_CONTAINER, onClick = {
            })
            AddButton(variant = ButtonVariant.SECONDARY_CONTAINER, onClick = {
                navController.navigate(MobileNavigationScreen.ChooseWorkoutType.route)
            })
        }

        Spacer(modifier = Modifier.height(12.dp))

        TrainingDetailsRenderItem(
            title = workouts[selectedWorkout].name,
            description = workouts[selectedWorkout].category,
            leadingButtonDisabled = selectedWorkout == 0,
            trailingButtonDisabled = selectedWorkout == workouts.size - 1,
            onLeadingButtonPressed = {
                selectedWorkout--
            },
            onTrailingButtonPressed = {
                selectedWorkout++
            },
            trailingIcon = { PerfilShaypado2Icon() }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(workouts[selectedWorkout].exercises.size) { index ->
                val title = workouts[selectedWorkout].exercises[index].title
                val description =
                    workouts[selectedWorkout].exercises[index].series + " x " + workouts[selectedWorkout].exercises[index].repetitions
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
                    }

                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }

    AppButton(
        onClick = {
            navController.navigate(MobileNavigationScreen.UserWorkout.shortName + "/${workouts[selectedWorkout].id}")
        },
        text = "Iniciar"
    )
}