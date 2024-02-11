package com.ufape.shaypado.ui.screens.signUp

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ufape.shaypado.ui.components.AppButton
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.BackButton
import com.ufape.shaypado.ui.components.TextType

@Composable
fun SignUpScreenBase(
    @StringRes title: Int,
    @StringRes buttonText: Int? = null,
    onButtonClicked: () -> Unit = {},
    navController: NavController,
    topTitleSpacing: Int = 56,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(
            start = 16.dp, end = 16.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(36.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(
                onClick = {
                    navController.popBackStack()
                }
            )
            AppText(
                textType = TextType.HEADLINE_MEDIUM,
                textAlignment = TextAlign.Center,
                text = title,
                fillWidth = true
            )
        }

        Spacer(modifier = Modifier.height(topTitleSpacing.dp))

        Column(
            modifier =
            Modifier
                .fillMaxHeight(0.85f)
                .verticalScroll(rememberScrollState())
        )
        {
            content()
        }

        if (buttonText != null) {
            AppButton(
                text = buttonText,
                onClick = {
                    onButtonClicked()
                }
            )
        }
    }
}