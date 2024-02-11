package com.ufape.shaypado.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ufape.shaypado.R

@Preview
@Composable
fun BackButton(
    onClick: () -> Unit = { },
    enabled: Boolean = true,
) {
    FilledIconButton(
        shape = RoundedCornerShape(8.dp),
        enabled = enabled,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
@Preview
fun AppButton(
    @StringRes text: Int = R.string.label,
    onClick: () -> Unit = { },
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    content: @Composable (() -> Unit)? = null
) {

    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
    ) {
        if (content != null) {
            content()
        } else {
            AppText(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                text = text,
                textType = TextType.TITLE_MEDIUM,
            )
        }
    }
}