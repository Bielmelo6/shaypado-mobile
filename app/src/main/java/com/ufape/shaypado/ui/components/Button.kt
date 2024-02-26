package com.ufape.shaypado.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ModeEdit
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ufape.shaypado.R

@Preview
@Composable
fun BackButton(
    enabled: Boolean = true,
    onClick: () -> Unit = { }
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
fun NextButton(
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
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Next",
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Preview
@Composable
fun AddButton(
    onClick: () -> Unit = { },
    enabled: Boolean = true,
) {
    FilledIconButton(
        shape = RoundedCornerShape(8.dp),
        enabled = enabled,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}


@Preview
@Composable
fun EditButton(
    enabled: Boolean = true,
    onClick: () -> Unit = { }
) {
    FilledIconButton(
        shape = RoundedCornerShape(8.dp),
        enabled = enabled,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(
            imageVector = Icons.Default.ModeEdit,
            contentDescription = "Add",
            tint = MaterialTheme.colorScheme.onPrimary
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
    TERTIARY,
    ERROR_CONTAINER
}

@Composable
@Preview
fun AppButton(
    @StringRes text: Int = R.string.label,
    onClick: () -> Unit = { },
    @StringRes errorMessage: Int? = null,
    backgroundColor: Color? = null,
    variant: ButtonVariant = ButtonVariant.PRIMARY,
    leftIcon: @Composable (() -> Unit)? = null
) {

    val containerColor = when (variant) {
        ButtonVariant.PRIMARY -> MaterialTheme.colorScheme.primaryContainer
        ButtonVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        ButtonVariant.SECONDARY_CONTAINER -> MaterialTheme.colorScheme.secondaryContainer
        ButtonVariant.TERTIARY -> MaterialTheme.colorScheme.tertiaryContainer
        ButtonVariant.ERROR_CONTAINER -> MaterialTheme.colorScheme.errorContainer
    }

    val textColor = when (variant) {
        ButtonVariant.SECONDARY_CONTAINER -> MaterialTheme.colorScheme.onSecondaryContainer
        ButtonVariant.ERROR_CONTAINER -> MaterialTheme.colorScheme.onErrorContainer
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leftIcon != null) {
                leftIcon()
                Spacer(modifier = Modifier.width(8.dp))
            }
            AppText(
                textAlignment = TextAlign.Center,
                text = text,
                textType = TextType.TITLE_MEDIUM,
                color = textColor
            )
        }
    }
    if (errorMessage != null) {
        AppText(
            textType = TextType.LABEL_MEDIUM,
            text = errorMessage,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
@Preview
fun AppButton(
    text: String = "Label",
    onClick: () -> Unit = { },
    @StringRes errorMessage: Int? = null,
    backgroundColor: Color? = null,
    variant: ButtonVariant = ButtonVariant.PRIMARY,
    leftIcon: @Composable (() -> Unit)? = null
) {

    val containerColor = when (variant) {
        ButtonVariant.PRIMARY -> MaterialTheme.colorScheme.primaryContainer
        ButtonVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        ButtonVariant.SECONDARY_CONTAINER -> MaterialTheme.colorScheme.secondaryContainer
        ButtonVariant.TERTIARY -> MaterialTheme.colorScheme.tertiaryContainer
        ButtonVariant.ERROR_CONTAINER -> MaterialTheme.colorScheme.errorContainer
    }

    val textColor = when (variant) {
        ButtonVariant.SECONDARY_CONTAINER -> MaterialTheme.colorScheme.onSecondaryContainer
        ButtonVariant.ERROR_CONTAINER -> MaterialTheme.colorScheme.onErrorContainer
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leftIcon != null) {
                leftIcon()
                Spacer(modifier = Modifier.width(8.dp))
            }
            AppText(
                textAlignment = TextAlign.Center,
                text = text,
                textType = TextType.TITLE_MEDIUM,
                color = textColor
            )
        }
    }
    if (errorMessage != null) {
        AppText(
            textType = TextType.LABEL_MEDIUM,
            text = errorMessage,
            color = MaterialTheme.colorScheme.error
        )
    }
}