package com.ufape.shaypado.ui.screens.trainer.userFriends

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.theme.StudentImage

@Composable
fun CardUserWorkout() {
    Card(
        modifier = Modifier
            .height(75.dp)
            .fillMaxWidth()
            .clickable {},
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                //StudentImage()
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .height(65.dp)
                        .width(65.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayCircleOutline,
                        contentDescription = "Workout",
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .width(225.dp)
                        .fillMaxSize()
                        .padding(start = 8.dp, end = 0.dp, top = 0.dp, bottom = 0.dp),
                    //horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    AppText(
                        text = "Iniciante - Superiores",
                        textType = TextType.TITLE_MEDIUM,
                        textAlignment = TextAlign.Start,
                    )
                    AppText(
                        text = "17x30",
                        textType = TextType.LABEL_SMALL,
                        textAlignment = TextAlign.Start,
                    )
                }
            }
            Row() {
                FilledIconButton(
                    shape = RoundedCornerShape(8.dp),
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    ),
                    modifier = Modifier
                        .width(35.dp)
                        .height(35.dp)
                        .padding(1.5.dp)

                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp)
                    )
                }
                FilledIconButton(
                    shape = RoundedCornerShape(8.dp),
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                    ),
                    modifier = Modifier
                        .width(35.dp)
                        .height(35.dp)
                        .padding(1.5.dp)

                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Remove",
                            tint = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier
                                .width(28.dp)
                                .height(28.dp)
                        )
                    }
                }
            }
        }
    }
    Divider(
        modifier = Modifier
            .height(2.dp)
            .fillMaxWidth(), color = Color(0xFFF4F4F4)
    )
}