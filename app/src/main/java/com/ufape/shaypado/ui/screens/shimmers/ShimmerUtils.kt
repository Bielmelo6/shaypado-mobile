package com.ufape.shaypado.ui.screens.shimmers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ufape.shaypado.util.shimmerEffect

@Composable
@Preview
fun TextShimmer(
    widthFraction: Float = 1f,
    useShimmer: Boolean = true
) {

    val modifier = Modifier
        .fillMaxWidth(widthFraction)
        .height(24.dp)
        .background(
            shape = RoundedCornerShape(8.dp),
            color = if (!useShimmer) MaterialTheme.colorScheme.background else Color.Transparent
        )

    if (useShimmer) {
        Row(
            modifier = modifier.shimmerEffect()
        ) {

        }
    } else {
        Box(
            modifier = modifier
        )
    }
}


@Composable
@Preview
fun RoundedIconShimmer(
    size: Int = 48,
    useShimmer: Boolean = true

) {
    val modifier = Modifier
        .size(size.dp)
        .background(
            shape = CircleShape,
            color = if (!useShimmer) MaterialTheme.colorScheme.background else Color.Transparent
        )
        .clip(CircleShape)

    if (useShimmer) {
        Box(
            modifier = modifier.shimmerEffect()
        )
    } else {
        Box(
            modifier = modifier
        )
    }
}
