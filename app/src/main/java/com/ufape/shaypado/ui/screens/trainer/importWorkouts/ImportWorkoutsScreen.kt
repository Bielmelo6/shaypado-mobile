package com.ufape.shaypado.ui.screens.trainer.importWorkouts

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.BackButton
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.model.WorkoutState
import com.ufape.shaypado.ui.theme.BarbellIcon
import com.ufape.shaypado.util.Result

@Composable
fun ImportWorkoutsScreen(
    navController: NavController,
    onImport: (List<WorkoutState>) -> Unit,
    onBackPressed : (() -> Unit)? = null
) {
    val importFriendsViewModel = hiltViewModel<ImportWorkoutsViewModel>()
    val isLoading = importFriendsViewModel.workoutData.collectAsState(
        initial = Result.Loading
    )

    LaunchedEffect(Unit) {
        importFriendsViewModel.fetchFriends()
    }

    fun backPressed() {
        if (onBackPressed != null) {
            onBackPressed()
        } else {
            navController.popBackStack()
        }
    }

    BackHandler {
        backPressed()
    }

    if (isLoading.value is Result.Loading)
        return Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            AppText(
                textType = TextType.TITLE_MEDIUM,
                text = R.string.loading,
                fillWidth = true
            )
        }

    if (isLoading.value is Result.Error)
        return Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            AppText(
                textType = TextType.TITLE_MEDIUM,
                text = R.string.error_loading_friends,
                fillWidth = true
            )
        }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton {
            backPressed()
        }

        AppText(
            textType = TextType.HEADLINE_MEDIUM,
            textAlignment = TextAlign.Center,
            text = R.string.import_workouts,
            fillWidth = true
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(importFriendsViewModel.workouts.size) {
            WorkoutCard(
                selected = importFriendsViewModel.workouts[it].isSelected,
                title = importFriendsViewModel.workouts[it].name,
                description = importFriendsViewModel.workouts[it].category,
                onSelected = {
                    importFriendsViewModel.toggleWorkout(it)
                }
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    AppButton(
        text = R.string.cancel,
        variant = ButtonVariant.SECONDARY_CONTAINER,
        onClick = {
            backPressed()
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    AppButton(
        text = R.string.button_import,
        variant = ButtonVariant.PRIMARY,
        onClick = {
            val selectedFriends =
                importFriendsViewModel.workouts.filter { it.isSelected }
            onImport(selectedFriends)
            backPressed()
        }
    )


}

@Composable
fun WorkoutCard(
    title: String ,
    description: String ,
    selected: Boolean = true,
    onSelected: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
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
            Column(
                modifier = Modifier.width(200.dp)
            ) {
                AppText(
                    fillWidth = true,
                    text = title,
                    textType = TextType.TITLE_MEDIUM
                )
                Spacer(modifier = Modifier.width(8.dp))
                AppText(
                    fillWidth = true,
                    text = description,
                    textType = TextType.TITLE_SMALL
                )

            }
        }

        Box(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = if (selected) MaterialTheme.colorScheme.tertiaryContainer else Color.Transparent
                )
                .border(
                    width = 2.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    onSelected()
                },
        )
    }

    Spacer(modifier = Modifier.height(8.dp))
    HorizontalDivider()
}