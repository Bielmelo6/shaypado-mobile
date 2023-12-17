package com.ufape.shaypado.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ufape.shaypado.ui.theme.textPrimary

@Composable
@Preview
fun Title(
    text: String = "Label",
    color: Color = textPrimary,
    onPress: () -> Unit = { }
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = color,
        modifier = Modifier.clickable { onPress() }
    )
}

@Composable
@Preview
fun TitleSmall(
    text: String = "Label",
    color: Color = textPrimary,
    onPress: () -> Unit = { }
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = color,
        modifier = Modifier.clickable { onPress() }
    )
}

@Composable
@Preview
fun Label(
    text: String = "Label",
    color: Color = textPrimary,
    onPress: (() -> Unit?)? = null
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = color,
        modifier = if (onPress != null) Modifier.clickable { onPress() } else Modifier
    )
}

@Composable
@Preview
fun LabelSmall(
    text: String = "Label",
    color: Color = textPrimary,
    onPress: (() -> Unit?)? = null
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = color,
        modifier = if (onPress != null) Modifier.clickable { onPress() } else Modifier
    )
}