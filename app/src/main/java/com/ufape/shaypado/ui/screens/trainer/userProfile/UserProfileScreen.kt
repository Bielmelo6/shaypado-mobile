package com.ufape.shaypado.ui.screens.trainer.userProfile

import android.graphics.drawable.shapes.Shape
import android.service.quicksettings.Tile
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.ShieldMoon
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.solver.widgets.Rectangle
import androidx.navigation.NavController
import com.google.android.material.color.utilities.Variant
import com.ufape.shaypado.R
import com.ufape.shaypado.exceptions.ApiErrorException
import com.ufape.shaypado.ui.components.AddButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.AppSnackBar
import com.ufape.shaypado.ui.components.EditButton
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.AuthNavigationScreen
import com.ufape.shaypado.ui.routes.Container
import com.ufape.shaypado.ui.theme.EmailIcon
import com.ufape.shaypado.ui.theme.GoogleImage
import com.ufape.shaypado.ui.theme.KeyIcon
import com.ufape.shaypado.ui.theme.ShaypadoImage
import com.ufape.shaypado.ui.theme.StudentImage
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getErrorMessage

@Composable
fun UserProfileScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(105.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Box(
                        modifier = Modifier
                            .width(90.dp)
                            .height(90.dp)
                    )
                    {
                        FloatingActionButton(
                            onClick = {},
                            modifier = Modifier.matchParentSize(),
                            shape = CircleShape,
                        ) {
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(bottom = 10.dp, top = 0.dp, start = 0.dp, end = 0.dp)
                    ) {
                        AppText(
                            text = String.format("bibia"),
                            textAlignment = TextAlign.Start,
                            textType = TextType.HEADLINE_SMALL,
                        )
                        AppText(
                            text = String.format("anabiavanderlei@gmail.com"),
                            textAlignment = TextAlign.Start,
                            textType = TextType.TITLE_SMALL,
                        )
                    }
                    EditButton(onClick = {}, variant = ButtonVariant.SECONDARY_CONTAINER)
                }
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth(), color = Color(0xFFF4F4F4)
                )
                Card(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .clickable {},
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black,
                    ),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.DarkMode,
                            contentDescription = "Dark Mode",
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                                .padding(
                                    start = 6.dp,
                                    end = 6.dp,
                                    top = 8.dp,
                                    bottom = 8.dp
                                )
                        )
                        AppText(
                            text = "Modo Noturno",
                            textType = TextType.TITLE_MEDIUM,
                            textAlignment = TextAlign.Start,
                        )
                    }
                }
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth(), color = Color(0xFFF4F4F4)
                )
                Card(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .clickable {},
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black,
                    ),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Friendships",
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                                .padding(
                                    start = 6.dp,
                                    end = 6.dp,
                                    top = 8.dp,
                                    bottom = 8.dp
                                )
                        )
                        AppText(
                            text = "Amizades",
                            textType = TextType.TITLE_MEDIUM,
                            textAlignment = TextAlign.Start,
                        )
                    }
                }
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth(), color = Color(0xFFF4F4F4)
                )
                Card(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .clickable {},
                    colors = CardColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Black,
                        disabledContentColor = Color.Black
                    ),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.FitnessCenter,
                            contentDescription = "Your Workout",
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                                .padding(
                                    start = 6.dp,
                                    end = 6.dp,
                                    top = 8.dp,
                                    bottom = 8.dp
                                )
                        )
                        AppText(
                            text = "Seus treinos",
                            textType = TextType.TITLE_MEDIUM,
                            textAlignment = TextAlign.Start,
                        )
                    }
                }
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth(), color = Color(0xFFF4F4F4)
                )
                Card(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .clickable {},
                    colors = CardColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Black,
                        disabledContentColor = Color.Black
                    ),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ExitToApp,
                            contentDescription = "Exit",
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                                .padding(
                                    start = 6.dp,
                                    end = 6.dp,
                                    top = 8.dp,
                                    bottom = 8.dp
                                )
                        )
                        AppText(
                            text = "Sair",
                            textType = TextType.TITLE_MEDIUM,
                            textAlignment = TextAlign.Start,
                        )
                    }
                }
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth(), color = Color(0xFFF4F4F4)
                )
            }
            OutlinedCard(
                modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.outline,
                ),
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.secondaryContainer
                ),
            )
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .padding(16.dp)
                ) {
                    AppText(
                        textType = TextType.BODY_MEDIUM,
                        textAlignment = TextAlign.Center,
                        text = "Seu código de amizade"
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    AppText(
                        textType = TextType.DISPLAY_LARGE,
                        textAlignment = TextAlign.Center,
                        text = "XYWXYZ",
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
            }
        }
    }
}




