package com.ufape.shaypado.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ufape.shaypado.R

@Preview
@Composable
fun CustomButton() {
    IconButton(

        modifier = Modifier
            .background(
                color = colorResource(id = R.color.primary),
                shape = CircleShape
            ),
        onClick = { /*TODO*/ }
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_at), contentDescription = "Google")
    }
}

@Composable
@Preview
fun CustomButton(
    text: String = "Button",
    onClick: () -> Unit = { },
    backgroundColor: Color = colorResource(id = R.color.primary),
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
        Text(text = text, color = colorResource(id = R.color.black))
    }
}