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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
fun CardFriend() {
    Card(
        modifier = Modifier
            .height(75.dp)
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
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row {
                //StudentImage()
                Box(
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp)
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
                    modifier = Modifier
                        .width(250.dp)
                        .padding(start = 8.dp, end = 0.dp, top = 0.dp, bottom = 0.dp),
                    //horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    AppText(
                        text = "PandaSonhador",
                        textType = TextType.TITLE_MEDIUM,
                        textAlignment = TextAlign.Start,
                    )
                    Card(modifier = Modifier
                        .width(85.dp)
                        .height(30.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        ),
                        ){
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            AppText(
                                textAlignment = TextAlign.Center,
                                textType = TextType.LABEL_SMALL,
                                text = "Iniciante",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                            )
                        }
                    }
                }
            }
            FilledIconButton(
                shape = RoundedCornerShape(8.dp),
                onClick = {},
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                ),
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Heart",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )
            }
        }
    }
    Divider(
        modifier = Modifier
            .height(2.dp)
            .fillMaxWidth(), color = Color(0xFFF4F4F4)
    )
}