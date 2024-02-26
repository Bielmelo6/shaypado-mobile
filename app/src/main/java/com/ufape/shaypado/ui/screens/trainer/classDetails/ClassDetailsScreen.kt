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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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

@Composable
fun ClassDetailsScreen(
    navController: NavController
) {
    var dropdownExpanded by rememberSaveable { mutableStateOf(false) }
    var usersDropdownExpanded by rememberSaveable { mutableStateOf(false) }

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
    ClassDetailsRenderItem()

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
                items(20) {
                    UserDetailsRenderItem(
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
                items(20) {
                    UserDetailsRenderItem(
                        leadingIcon = {
                            StudentImage()
                        },
                        onPress = {
                            navController.navigate(TrainerNavigationScreen.StudentDetails.route)
                        },
                    )
                }
            }
        }
    }
}