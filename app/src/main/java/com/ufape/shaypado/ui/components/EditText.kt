package com.ufape.shaypado.ui.components

import android.widget.Space
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
import androidx.compose.material3.Icon
import androidx.compose.ui.focus.onFocusChanged

@Preview
@Composable
fun CustomTextField(
    value: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: @Composable (() -> Unit)? = null,
    @StringRes placeholder: Int = R.string.label,
    @StringRes label: Int = R.string.label,
    @StringRes errorMessage: Int? = null,
    onValueChange: (String) -> Unit = {},
    trailingIcon: @Composable (() -> Unit) = {},
    format: (String) -> String = { it },
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val hasError = errorMessage != null
    val errorColor = if (hasError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
    val focusedBorderColor = if (hasError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary

    Column(modifier = Modifier.fillMaxWidth())
    {

        AppText(
            textType = TextType.TITLE_MEDIUM,
            text = label,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            value = value,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            onValueChange = {
                val formattedData = format(it)
                onValueChange(formattedData)
            },
            isError = hasError,
            shape = RoundedCornerShape(8.dp),
            textStyle = MaterialTheme.typography.labelMedium.plus(TextStyle(color = textSecondary)),
            singleLine = true,
            placeholder = {
                AppText(
                    textType = TextType.LABEL_LARGE,
                    text = placeholder,
                    color = errorColor
                )
            },
            visualTransformation = if (keyboardType == KeyboardType.Password && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = Color.Red,
                focusedBorderColor = focusedBorderColor,
                focusedContainerColor =  Color.Transparent,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            ),
            leadingIcon = leadingIcon ,
            trailingIcon = {
                if (keyboardType == KeyboardType.Password) {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        if (passwordVisible)
                            EyeIcon()
                        else
                            EyeSlashIcon()
                    }
                }else{
                    trailingIcon.invoke()
                }
            }
        )
        if (hasError) {
            AppText(
                textType = TextType.LABEL_MEDIUM,
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}