package com.ufape.shaypado.ui.screens.shimmers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ufape.shaypado.util.shimmerEffect

@Composable
@Preview
fun TrainerHomeShimmer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(8.dp),
                color = Color.Transparent
            )
            .shimmerEffect()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            TextShimmer(

                useShimmer = false
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextShimmer(
                widthFraction = 0.5f,
                useShimmer = false
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextShimmer(
                widthFraction = 0.5f,
                useShimmer = false
            )
        }
        RoundedIconShimmer(
            useShimmer = false
        )

    }

    Spacer(modifier = Modifier.height(16.dp))

    for (i in 0..8) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 14.dp,
                    bottom = 14.dp,
                    end = 16.dp
                )
        ) {
            RoundedIconShimmer()
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                TextShimmer()

                Spacer(modifier = Modifier.height(8.dp))

                TextShimmer(
                    widthFraction = 0.5f
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}