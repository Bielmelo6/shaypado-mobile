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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.model.TrainerProfileData
import com.ufape.shaypado.ui.routes.TrainerNavigationScreen
import com.ufape.shaypado.ui.screens.shimmers.TextShimmer
import com.ufape.shaypado.ui.theme.BarbellIcon
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.copyToClipboard
import com.ufape.shaypado.util.getErrorMessage

@Composable
fun SettingsScreen(
    navController: NavController,
    onLogout: () -> Unit,
    showSnackBar: (String) -> Unit
) {
    val context = LocalContext.current

    val viewModel = hiltViewModel<SettingsViewModel>()
    var userStatus by remember { mutableStateOf<Result<TrainerProfileData>>(Result.Loading) }


    LaunchedEffect(Unit) {
        viewModel.fetchUserProfileData()
    }

    LaunchedEffect(key1 = viewModel.userProfileData) {
        viewModel.userProfileData.collect {
            userStatus =  if (it is Result.Error){
                showSnackBar(it.exception.getErrorMessage(context))
                it
            }else {
                it
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(TrainerNavigationScreen.UpdateProfile.route)
            }
    ) {
        Image(
            modifier = Modifier.size(60.dp),
            painter = painterResource(id = R.drawable.ic_student),
            contentDescription = "User Pet"
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            if (userStatus is Result.Success) {
                val user = (userStatus as Result.Success).data
                AppText(
                    text = user.name,
                    textType = TextType.HEADLINE_SMALL,
                )

                AppText(
                    text = user.email,
                    textType = TextType.TITLE_SMALL,
                )
            } else {
                TextShimmer(0.8f)
                Spacer(modifier = Modifier.height(4.dp))
                TextShimmer(0.5f)
            }

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
        if (userStatus is Result.Success) {
            val user = (userStatus as Result.Success).data
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
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(139.dp)
                    .border(
                        width = 3.dp,
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(size = 8.dp)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextShimmer(0.8f)

                Spacer(modifier = Modifier.height(16.dp))

                TextShimmer(0.5f)
            }
        }
    }
}
