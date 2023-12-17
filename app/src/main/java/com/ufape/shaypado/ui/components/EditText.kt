package com.ufape.shaypado.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.ufape.shaypado.ui.theme.textSecondary

@Preview
@Composable
fun CustomTextField(
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: String = ""
) {
    var textState by remember { mutableStateOf("") }
    val containerColor = MaterialTheme.colorScheme.surface
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        onValueChange = { textState = it },
        shape = RoundedCornerShape(15.dp),
        textStyle = MaterialTheme.typography.labelMedium.plus(TextStyle(color = textSecondary)),
        singleLine = true,
        placeholder = { Text(placeholder, style = MaterialTheme.typography.labelMedium) },
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        leadingIcon = leadingIcon,
    )
}

@Composable
@Preview
fun CustomPasswordTextField(
    leadingIcon: @Composable (() -> Unit)? = null
) {
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val containerColor = MaterialTheme.colorScheme.surface
    TextField(
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        leadingIcon = leadingIcon,
        value = password,
        shape = RoundedCornerShape(15.dp),
        onValueChange = { password = it },
        singleLine = true,
        placeholder = { Text("Password", style = MaterialTheme.typography.labelMedium) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Close
            else Icons.Filled.Check

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        }
    )
}

