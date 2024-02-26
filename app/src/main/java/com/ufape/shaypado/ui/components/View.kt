package com.ufape.shaypado.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Header(
    modifier: Modifier = Modifier,
    navController: NavController,
    @StringRes title: Int,
    space: Int = 16,
    trailingContent: @Composable () -> Unit = {},

    ) {
    Spacer(modifier = Modifier.height(space.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(
            onClick = { navController.popBackStack() }
        )

        AppText(
            textAlignment = TextAlign.Center,
            textType = TextType.HEADLINE_MEDIUM,
            text = title,
        )
        Row  {

        }
    }
    Spacer(modifier = Modifier.height(space.dp))
}


@Composable
@Preview
fun HeaderPreview() {
    Header(
        title = 0,
        navController = rememberNavController(),
        trailingContent = {
            NextButton(
                onClick = { },
                enabled = true
            )
        }
    )
}