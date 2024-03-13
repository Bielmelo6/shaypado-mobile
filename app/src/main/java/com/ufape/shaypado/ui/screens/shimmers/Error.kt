package com.ufape.shaypado.ui.screens.shimmers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.TextType

@Composable
fun ErrorScreen(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.9f),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.shaypado_email),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )
        AppText(
            text = R.string.try_again_message,
            textType = TextType.TITLE_MEDIUM,
            fillWidth = true,
            textAlignment = TextAlign.Center
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    AppButton(
        text = R.string.try_again,
        onClick = {
            onClick()
        },
    )
}