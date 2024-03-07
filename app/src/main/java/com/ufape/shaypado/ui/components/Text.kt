package com.ufape.shaypado.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.theme.textPrimary

enum class TextType {
    TITLE_LARGE,
    TITLE_MEDIUM,
    TITLE_SMALL,
    LABEL_MEDIUM,
    LABEL_SMALL,
    LABEL_LARGE,
    BODY_LARGE,
    BODY_MEDIUM,
    HEADLINE_LARGE,
    HEADLINE_MEDIUM,
    HEADLINE_SMALL,
    DISPLAY_SMALL,
    DISPLAY_LARGE
}

@Composable
@Preview
fun AppText(
    textType: TextType = TextType.TITLE_LARGE,
    @StringRes text: Int = R.string.label,
    color: Color = textPrimary,
    size : TextUnit? = null,
    onPress: (() -> Unit)? = null,
    textAlignment: TextAlign = TextAlign.Start,
    fillWidth: Boolean = false,
) {
    var style = when (textType) {
        TextType.TITLE_LARGE -> MaterialTheme.typography.titleLarge
        TextType.TITLE_MEDIUM -> MaterialTheme.typography.titleMedium
        TextType.TITLE_SMALL -> MaterialTheme.typography.titleSmall
        TextType.LABEL_MEDIUM -> MaterialTheme.typography.labelMedium
        TextType.LABEL_SMALL -> MaterialTheme.typography.labelSmall
        TextType.LABEL_LARGE -> MaterialTheme.typography.labelLarge
        TextType.BODY_LARGE -> MaterialTheme.typography.bodyLarge
        TextType.BODY_MEDIUM -> MaterialTheme.typography.bodyMedium
        TextType.HEADLINE_LARGE -> MaterialTheme.typography.headlineLarge
        TextType.HEADLINE_MEDIUM -> MaterialTheme.typography.headlineMedium
        TextType.HEADLINE_SMALL -> MaterialTheme.typography.headlineSmall
        TextType.DISPLAY_SMALL -> MaterialTheme.typography.displaySmall
        TextType.DISPLAY_LARGE -> MaterialTheme.typography.displayLarge
    }

    style = style.copy(
        textAlign = textAlignment,
        fontSize = size ?: style.fontSize,
    )

    val modifier = if (fillWidth) Modifier.fillMaxWidth() else Modifier

    Text(
        text = stringResource(id = text),
        style = style,
        color = color,
        modifier = if (onPress != null) modifier.clickable { onPress() } else modifier
    )
}

@Composable
@Preview
fun AppText(
    modifier: Modifier = Modifier,
    textType: TextType = TextType.TITLE_LARGE,
    text: String = "R.string.label",
    color: Color = textPrimary,
    size : TextUnit? = null,
    onPress: (() -> Unit)? = null,
    textAlignment: TextAlign = TextAlign.Start,
    fillWidth: Boolean = false,
) {
    var style = when (textType) {
        TextType.TITLE_LARGE -> MaterialTheme.typography.titleLarge
        TextType.TITLE_MEDIUM -> MaterialTheme.typography.titleMedium
        TextType.TITLE_SMALL -> MaterialTheme.typography.titleSmall
        TextType.LABEL_MEDIUM -> MaterialTheme.typography.labelMedium
        TextType.LABEL_SMALL -> MaterialTheme.typography.labelSmall
        TextType.LABEL_LARGE -> MaterialTheme.typography.labelLarge
        TextType.BODY_LARGE -> MaterialTheme.typography.bodyLarge
        TextType.BODY_MEDIUM -> MaterialTheme.typography.bodyMedium
        TextType.HEADLINE_LARGE -> MaterialTheme.typography.headlineLarge
        TextType.HEADLINE_MEDIUM -> MaterialTheme.typography.headlineMedium
        TextType.HEADLINE_SMALL -> MaterialTheme.typography.headlineSmall
        TextType.DISPLAY_SMALL -> MaterialTheme.typography.displaySmall
        TextType.DISPLAY_LARGE -> MaterialTheme.typography.displayLarge
    }

    style = style.copy(
        textAlign = textAlignment,
        fontSize = size ?: style.fontSize,
    )

    val modifierA = if (fillWidth) modifier.fillMaxWidth() else Modifier

    Text(
        text = text,
        style = style,
        color = color,
        modifier = if (onPress != null) modifierA.clickable { onPress() } else modifier
    )
}