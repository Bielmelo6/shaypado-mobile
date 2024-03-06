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
import com.ufape.shaypado.ui.screens.trainer.userFriends.CardFriend
import com.ufape.shaypado.ui.screens.trainer.userFriends.CardUserWorkout
import com.ufape.shaypado.ui.theme.EmailIcon
import com.ufape.shaypado.ui.theme.GoogleImage
import com.ufape.shaypado.ui.theme.KeyIcon
import com.ufape.shaypado.ui.theme.ShaypadoImage
import com.ufape.shaypado.ui.theme.StudentImage
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getErrorMessage

@Composable
fun UserWorkoutScreen(navController: NavController){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(16.dp)) {
        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.matchParentSize()){
            Column{
                AppHeader(
                    navController = navController,
                    trailingContent = {
                        AddButton(
                            variant = ButtonVariant.SECONDARY_CONTAINER,
                            onClick = {}
                        )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                CardUserWorkout()
                CardUserWorkout()
            }
        }
    }
}