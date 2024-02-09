package com.ufape.shaypado.ui.components

import androidx.compose.material3.IconButton
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.RichTooltipState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.ufape.shaypado.ui.theme.InfoIcon
import kotlinx.coroutines.launch


@Composable
fun Tooltip(
    title: String,
    onClose: () -> Unit = {},
    text: String ,
) {
    val tooltipState by remember { mutableStateOf(RichTooltipState()) }
    val scope = rememberCoroutineScope()
    RichTooltipBox(
        title = { Text(title) },
        action = {
            TextButton(
                onClick = { scope.launch {
                    tooltipState.dismiss()
                    onClose()
                } }
            ) { Text("Okay") }
        },
        text = { Text(text) },
        tooltipState = tooltipState
    ) {
        IconButton(
            onClick = { scope.launch { tooltipState.show() }}
        ) {
            InfoIcon()
        }
    }
}