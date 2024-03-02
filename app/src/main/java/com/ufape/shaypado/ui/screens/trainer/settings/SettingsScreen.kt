package com.ufape.shaypado.ui.screens.trainer.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.TrainerNavigationScreen
import com.ufape.shaypado.ui.theme.BarbellIcon
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.copyToClipboard

@Composable
fun SettingsScreen(
    navController: NavController,
    onLogout: () -> Unit
) {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val userProfileData by viewModel.userProfileData.collectAsState(
        initial = Result.Loading
    )

    LaunchedEffect(Unit) {
        viewModel.fetchUserProfileData()
    }

    if (userProfileData is Result.Error) {
        AppText(
            text = "Error",
            color = Color.Red
        )
        return
    }

    if (userProfileData is Result.Loading) {
        AppText(
            text = "Loading",
            color = Color.Black
        )
        return
    }

    val user = (userProfileData as Result.Success).data
    val context = LocalContext.current

    Row {
        Image(
            modifier = Modifier.size(60.dp),
            painter = painterResource(id = R.drawable.ic_student),
            contentDescription = "User Pet"
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            AppText(
                text = user.name,
                textType = TextType.HEADLINE_SMALL,
            )

            AppText(
                text = user.email,
                textType = TextType.TITLE_SMALL,
            )

        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    HorizontalDivider()

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_moonstars),
            contentDescription = "Nightmode"
        )
        Spacer(modifier = Modifier.width(8.dp))

        AppText(
            text = "Modo noturno",
            textType = TextType.TITLE_MEDIUM,
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    HorizontalDivider()

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.clickable {
            navController.navigate(TrainerNavigationScreen.Friends.route)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_heart),
            contentDescription = "Firendships"
        )
        Spacer(modifier = Modifier.width(8.dp))

        AppText(
            text = "Amizades",
            textType = TextType.TITLE_MEDIUM,
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    HorizontalDivider()

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.clickable {
            navController.navigate(TrainerNavigationScreen.Workouts.route)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        BarbellIcon()
        Spacer(modifier = Modifier.width(8.dp))

        AppText(
            text = "Treinos",
            textType = TextType.TITLE_MEDIUM,
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    HorizontalDivider()

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.clickable {
            onLogout()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_exit_to_app),
            contentDescription = "Sair do app"
        )
        Spacer(modifier = Modifier.width(8.dp))

        AppText(
            text = "Sair",
            textType = TextType.TITLE_MEDIUM,
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    HorizontalDivider()

    Spacer(modifier = Modifier.height(8.dp))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(139.dp)
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .clickable {
                    context.copyToClipboard(
                        label = "Código de amizade",
                        text = user.friendshipCode
                    )
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppText(
                text = "Seu código de amizade é",
                textType = TextType.BODY_MEDIUM,
            )

            Spacer(modifier = Modifier.height(16.dp))
            AppText(
                text = user.friendshipCode,
                textType = TextType.DISPLAY_LARGE,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}
