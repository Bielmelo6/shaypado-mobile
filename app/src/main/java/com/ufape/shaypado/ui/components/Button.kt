package com.ufape.shaypado.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

@Preview
@Composable
fun CameraButton(
    onClick: () -> Unit = { },
    enabled: Boolean = true,
) {
    FilledIconButton(
        modifier = Modifier
            .width(56.dp)
            .height(56.dp),
        shape = RoundedCornerShape(8.dp),
        enabled = enabled,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_camera),
            contentDescription = "Camera",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

enum class ButtonVariant {
    PRIMARY,
    SECONDARY,
    SECONDARY_CONTAINER,
    TERTIARY
}

@Composable
@Preview
fun AppButton(
    @StringRes text: Int = R.string.label,
    onClick: () -> Unit = { },
    backgroundColor: Color? = null,
    variant : ButtonVariant = ButtonVariant.PRIMARY,
    leftIcon: @Composable (() -> Unit)? = null
) {

    val containerColor = when (variant) {
        ButtonVariant.PRIMARY -> MaterialTheme.colorScheme.primaryContainer
        ButtonVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        ButtonVariant.SECONDARY_CONTAINER -> MaterialTheme.colorScheme.secondaryContainer
        ButtonVariant.TERTIARY -> MaterialTheme.colorScheme.tertiaryContainer
    }

    val textColor = when (variant) {
        ButtonVariant.SECONDARY_CONTAINER -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onPrimaryContainer
    }

    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor ?: containerColor
        ),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leftIcon != null) {
                leftIcon()
                Spacer(modifier = Modifier.width(8.dp))
            }
            AppText(
                text = text,
                textType = TextType.TITLE_MEDIUM,
                color = textColor
            )
        }
    }
}