package com.ufape.shaypado.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AppHeader(
    modifier: Modifier = Modifier,
    navController: NavController,
    @StringRes title: Int? = null,
    titleFillWidth: Boolean = false,
    onBackPressed: () -> Unit = { navController.popBackStack() },
    trailingContent: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(
            onClick = { onBackPressed() }
        )

        if (title != null) {
            AppText(
                fillWidth = titleFillWidth,
                textAlignment = TextAlign.Center,
                textType = TextType.HEADLINE_MEDIUM,
                text = title,
            )
        } else {
            Row {

            }
        }

        if (trailingContent != null) {
            trailingContent()
        } else {
            Row {

            }
        }
    }
}

@Composable
fun AppDialog (
    onDismiss: () -> Unit,
    isDialogVisible : Boolean,
    content: @Composable () -> Unit
) {
    if (isDialogVisible) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
        ) {
            Surface(

                shape = MaterialTheme.shapes.extraLarge,
                tonalElevation = 5.dp,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(
                        shape = MaterialTheme.shapes.extraLarge,
                        color = MaterialTheme.colorScheme.surface
                    ),
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    content()
                }
            }
        }
    }
}


@Composable
@Preview
fun HeaderPreview() {
    AppHeader(
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