package com.ufape.shaypado.ui.screens.trainer.userFriends

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ufape.shaypado.ui.components.AddButton
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.TextType


@Composable
fun UserFriendsScreen(navController: NavController) {
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
                CardFriend()
                CardFriend()
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