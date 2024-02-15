package com.ufape.shaypado.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(heightDp = 100)
fun CustomCard(elevation: Int = 24, shape: Shape = CardDefaults.elevatedShape, content: @Composable () -> Unit = {}) {
    val cardColors = CardDefaults.cardColors(
        contentColor = Color.Transparent,
        containerColor = Color.Transparent
    )

    ElevatedCard(
        colors = cardColors,
        shape = shape,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        content()
    }
}