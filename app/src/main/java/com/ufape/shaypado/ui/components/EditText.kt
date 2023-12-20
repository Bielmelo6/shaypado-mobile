package com.ufape.shaypado.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.theme.EyeIcon
import com.ufape.shaypado.ui.theme.EyeSlashIcon
import com.ufape.shaypado.ui.theme.textSecondary

@Preview
@Composable
fun CustomTextField(
    value: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: @Composable (() -> Unit)? = null,
    @StringRes placeholder: Int = R.string.label,
    errorMessage: String? = null,
    onValueChange: (String) -> Unit = {},
    format: (String) -> String = { it },
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val containerColor = MaterialTheme.colorScheme.surface
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        onValueChange = {
            val formattedData = format(it)
            onValueChange(formattedData)
        },
        isError = errorMessage != null,
        shape = RoundedCornerShape(15.dp),
        textStyle = MaterialTheme.typography.labelMedium.plus(TextStyle(color = textSecondary)),
        singleLine = true,
        placeholder = { AppText(textType = TextType.LABEL_MEDIUM, text = placeholder) },
        visualTransformation = if (keyboardType == KeyboardType.Password && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (keyboardType == KeyboardType.Password) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    if (passwordVisible)
                        EyeIcon()
                    else
                        EyeSlashIcon()
                }
            }
        }
    )
}
