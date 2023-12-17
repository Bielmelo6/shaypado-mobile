package com.ufape.shaypado.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.theme.textPrimary

enum class RoundedButtonType {
    BACK,

}

@Preview
@Composable
fun RoundedButton(
    onClick: () -> Unit = { },
    enabled : Boolean = true,
    type: RoundedButtonType = RoundedButtonType.BACK,
) {
    FilledIconButton(
        enabled = enabled,
        onClick = onClick
    ) {
        when (type) {
            RoundedButtonType.BACK -> Icon(painterResource(id = R.drawable.ic_back), contentDescription = "Back")
        }
    }
}

@Composable
@Preview
fun FullWidthButton(
    text: String = "Title",
    onClick: () -> Unit = { },
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    content: @Composable (() -> Unit)? = null
) {

    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(20.dp),
        onClick = onClick,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
    ) {
        if (content != null) {
            content()
        }else {
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = textPrimary
            )
        }
    }
}